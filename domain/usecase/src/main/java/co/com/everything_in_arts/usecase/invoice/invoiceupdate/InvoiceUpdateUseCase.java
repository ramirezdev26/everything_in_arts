package co.com.everything_in_arts.usecase.invoice.invoiceupdate;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class InvoiceUpdateUseCase implements BiFunction<String, Invoice, Mono<Invoice>> {

    private final InvoiceRepository repository;

    @Override
    public Mono<Invoice> apply(String id, Invoice invoice) {
        return repository.updateInvoice(id, invoice);
    }
}