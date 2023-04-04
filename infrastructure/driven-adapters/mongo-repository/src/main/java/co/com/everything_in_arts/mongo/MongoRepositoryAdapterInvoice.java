package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.mongo.data.InvoiceData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterInvoice implements InvoiceRepository {

    private final MongoDBRepositoryInvoice repository;

    private final ObjectMapper mapper;


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
                .switchIfEmpty(Mono.empty())
                .map(invoiceData -> mapper.map(invoiceData, Invoice.class));
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
}
