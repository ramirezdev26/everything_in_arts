package co.com.everything_in_arts.usecase.invoice.invoicegetbyid;

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
class InvoiceGetByIdUseCaseTest {

    @Mock
    InvoiceRepository repository;
    InvoiceGetByIdUseCase useCase;

    @BeforeEach
    void init(){  useCase = new InvoiceGetByIdUseCase(repository); }


    @Test
    @DisplayName("InvoiceGetByIdUseCase")
    void apply() {
        String id = "642c32a2470b822002324c83";
        Invoice invoice = new Invoice();

        Mockito.when(repository.getInvoiceById(id)).thenReturn(Mono.just(invoice));

        var execute = useCase.apply(id);

        StepVerifier.create(execute)
                .expectNext(invoice)
                .verifyComplete();

        Mockito.verify(repository).getInvoiceById(id);

    }

}