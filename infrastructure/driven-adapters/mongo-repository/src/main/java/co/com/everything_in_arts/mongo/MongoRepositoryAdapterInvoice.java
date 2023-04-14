package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.mongo.data.InvoiceData;
import co.com.everything_in_arts.mongo.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterInvoice implements InvoiceRepository {

    private final MongoDBRepositoryInvoice repository;

    private final ObjectMapper mapper;
    private final EmailService emailService;


    @Override
    public Flux<Invoice> getAllInvoices() {
        return this.repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(invoiceData -> mapper.map(invoiceData, Invoice.class));
    }

    @Override
    public Mono<Invoice> getInvoiceById(String id) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("invoice with id: " + id + "was not found")))
                .map(InvoiceData -> mapper.map(InvoiceData, Invoice.class));
    }

    @Override
    public Mono<Invoice> saveInvoice(Invoice invoice) {
        return this.repository
                .save(mapper.map(invoice, InvoiceData.class))
                .flatMap(savedInvoiceData -> {
                    // Send email to the customer
                    emailService.send("vdt316@gmail.com",
                            invoice.getEmail(),
                            "Thanks for your Purchase",
                            "Dear " + invoice.getName() + ",\n\n" +
                                    "Thank you for your recent purchase from Everything In Arts. We appreciate your trust in us and we hope you are enjoying your new items.\n\n"
                                    + "As a reminder, these are the items you purchased:\n\n"
                                    + formatItemList(savedInvoiceData.getItemList()) + "\n\n" +
                                    "If you have any questions or concerns regarding your purchase, please don't hesitate to reach out to our customer service team at [Phone Number] or [Email Address]. We are always here to help.\n\n" +
                                    "Thank you again for your business and we look forward to serving you in the future.\n\n" +
                                    "Best regards,\n" +
                                    "Everything In Arts");
                    // Map the saved invoice data to Invoice class
                    return Mono.just(mapper.map(savedInvoiceData, Invoice.class));
                })
                .switchIfEmpty(Mono.empty());
    }


    @Override
    public Mono<Invoice> updateInvoice(String id, Invoice invoice) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("invoice with id: " + id + " was not found")))
                .flatMap(invoiceData -> {
                    invoice.setId(invoiceData.getId());
                    return repository.save(mapper.map(invoice, InvoiceData.class));
                })
                .map(invoiceData -> mapper.map(invoiceData, Invoice.class));
    }

    @Override
    public Mono<String> deleteInvoice(String id) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("invoice with id: " + id + " was not found")))
                .flatMap(invoiceData -> this.repository.delete(invoiceData).thenReturn(id));
    }

    private String formatItemList(Set<Item> itemList) {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemList) {
            sb.append("- ");
            sb.append(item.getName());
            sb.append(" (");
            sb.append(item.getPrice());
            sb.append(")\n");
        }
        return sb.toString();
    }

}
