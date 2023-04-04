package co.com.everything_in_arts.usecase.item.itemgetall;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import co.com.everything_in_arts.usecase.invoice.invoicegetall.InvoiceGetAllUseCase;
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
class ItemGetAllUseCaseTest {

    @Mock
    ItemRepository repository;
    ItemGetAllUseCase useCase;

    @BeforeEach
    void init(){  useCase = new ItemGetAllUseCase(repository); }


    @Test
    @DisplayName("ItemGetAllUseCase")
    void get() {

        Mockito.when(repository.getAllItems()).thenReturn(Flux.just(new Item(), new Item()));

        var execute = useCase.get();

        StepVerifier.create(execute)
                .expectNextMatches(Objects::nonNull)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).getAllItems();

    }

}