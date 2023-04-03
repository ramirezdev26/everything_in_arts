package co.com.everything_in_arts.usecase.invoicedelete;

import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class InvoiceDeleteUseCase implements Function<String, Mono<Void>> {

    private final InvoiceRepository repository;


    @Override
    public Mono<Void> apply(String id) {
        return repository.deleteInvoice(id);
    }

}
