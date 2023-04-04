package co.com.everything_in_arts.model.invoice.gateways;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.item.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvoiceRepository {

    Flux<Invoice> getAllInvoices();
    Mono<Invoice> getInvoiceById(String id);
    Mono<Invoice> saveInvoice(Invoice invoice);
    Mono<Invoice> updateInvoice(String id, Invoice invoice);
    Mono<String> deleteInvoice(String id);

}
