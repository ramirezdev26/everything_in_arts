package co.com.everything_in_arts.usecase.invoice.invoiceupdate;

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


@ExtendWith(MockitoExtension.class)
class InvoiceUpdateUseCaseTest {

    @Mock
    InvoiceRepository repository;
    InvoiceUpdateUseCase useCase;

    @BeforeEach
    void init(){  useCase = new InvoiceUpdateUseCase(repository); }


    @Test
    @DisplayName("InvoiceUpdateUseCase")
    void apply() {
        String id = "642c32a2470b822002324c83";
        Invoice invoice = new Invoice();

        Mockito.when(repository.updateInvoice(id ,invoice)).thenReturn(Mono.just(invoice));

        var execute = useCase.apply(id ,invoice);

        StepVerifier.create(execute)
                .expectNext(invoice)
                .verifyComplete();

        Mockito.verify(repository).updateInvoice(id ,invoice);

    }

}