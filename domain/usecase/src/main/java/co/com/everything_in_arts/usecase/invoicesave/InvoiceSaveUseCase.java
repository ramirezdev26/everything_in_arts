package co.com.everything_in_arts.usecase.invoicesave;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class InvoiceSaveUseCase implements Function<Invoice, Mono<Invoice>> {

    private final InvoiceRepository repository;

    @Override
    public Mono<Invoice> apply(Invoice invoice) {
        return repository.saveInvoice(invoice);
    }
}
