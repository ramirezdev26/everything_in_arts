package co.com.everything_in_arts.usecase.invoice.invoicegetbyid;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class InvoiceGetByIdUseCase implements Function<String, Mono<Invoice>> {

    private final InvoiceRepository repository;

    @Override
    public Mono<Invoice> apply(String id) {
        return repository.getInvoiceById(id);
    }
}

