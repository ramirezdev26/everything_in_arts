package co.com.everything_in_arts.usecase.invoice.invoicegetall;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InvoiceGetAllUseCaseTest {

    @Mock
    InvoiceRepository repository;
    InvoiceGetAllUseCase useCase;

    @BeforeEach
    void init(){  useCase = new InvoiceGetAllUseCase(repository); }


    @Test
    @DisplayName("InvoiceGetAllUseCase")
    void get() {

        Mockito.when(repository.getAllInvoices()).thenReturn(Flux.just(new Invoice(), new Invoice()));

        var execute = useCase.get();

        StepVerifier.create(execute)
                .expectNextMatches(Objects::nonNull)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).getAllInvoices();

    }
}