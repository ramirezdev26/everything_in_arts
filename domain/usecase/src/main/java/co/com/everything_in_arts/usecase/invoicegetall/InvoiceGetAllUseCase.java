package co.com.everything_in_arts.usecase.invoicegetall;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class InvoiceGetAllUseCase implements Supplier<Flux<Invoice>> {

    private final InvoiceRepository repository;


    @Override
    public Flux<Invoice> get() {
        return repository.getAllInvoices();
    }
}
