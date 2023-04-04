package co.com.everything_in_arts.usecase.invoice.invoicesave;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InvoiceSaveUseCaseTest {

    @Mock
    InvoiceRepository repository;
    InvoiceSaveUseCase useCase;

    @BeforeEach
    void init(){  useCase = new InvoiceSaveUseCase(repository); }


    @Test
    @DisplayName("InvoiceSaveUseCase")
    void apply() {
        Invoice invoice = new Invoice();

        Mockito.when(repository.saveInvoice(invoice)).thenReturn(Mono.just(invoice));

        var execute = useCase.apply(invoice);

        StepVerifier.create(execute)
                .expectNext(invoice)
                .verifyComplete();

        Mockito.verify(repository).saveInvoice(invoice);

    }


}