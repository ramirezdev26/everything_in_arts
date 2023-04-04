package co.com.everything_in_arts.usecase.invoice.invoicedelete;

import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class InvoiceDeleteUseCase implements Function<String, Mono<String>> {

    private final InvoiceRepository repository;


    @Override
    public Mono<String> apply(String id) {
        return repository.deleteInvoice(id);
    }

}
