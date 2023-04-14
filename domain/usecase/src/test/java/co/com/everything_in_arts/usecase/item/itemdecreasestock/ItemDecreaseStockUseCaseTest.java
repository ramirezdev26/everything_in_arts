package co.com.everything_in_arts.usecase.item.itemdecreasestock;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import co.com.everything_in_arts.usecase.invoice.invoicedelete.InvoiceDeleteUseCase;
import co.com.everything_in_arts.usecase.item.itemdelete.ItemDeleteUseCase;
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
class ItemDecreaseStockUseCaseTest {

    @Mock
    ItemRepository repository;
    ItemDecreaseStockUseCase useCase;

    @BeforeEach
    void init(){  useCase = new ItemDecreaseStockUseCase(repository); }


    @Test
    @DisplayName("ItemStockReducedUseCase")
    void apply() {
        String id = "642c32a2470b822002324c83";

        Mockito.when(repository.decreaseStock(id)).thenReturn(Mono.just(id));

        var execute = useCase.apply(id);

        StepVerifier.create(execute)
                .expectNext(id)
                .verifyComplete();

        Mockito.verify(repository).decreaseStock(id);

    }

}