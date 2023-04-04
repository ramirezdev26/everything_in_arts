package co.com.everything_in_arts.usecase.item.itemsave;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import co.com.everything_in_arts.usecase.invoice.invoicesave.InvoiceSaveUseCase;
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
class ItemSaveUseCaseTest {

    @Mock
    ItemRepository repository;
    ItemSaveUseCase useCase;

    @BeforeEach
    void init(){  useCase = new ItemSaveUseCase(repository); }


    @Test
    @DisplayName("ItemSaveUseCase")
    void apply() {
        Item item = new Item();

        Mockito.when(repository.saveItem(item)).thenReturn(Mono.just(item));

        var execute = useCase.apply(item);

        StepVerifier.create(execute)
                .expectNext(item)
                .verifyComplete();

        Mockito.verify(repository).saveItem(item);

    }

}