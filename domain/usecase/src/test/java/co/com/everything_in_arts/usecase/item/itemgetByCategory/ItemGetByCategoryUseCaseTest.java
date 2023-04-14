package co.com.everything_in_arts.usecase.item.itemgetByCategory;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import co.com.everything_in_arts.usecase.item.itemgetall.ItemGetAllUseCase;
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
class ItemGetByCategoryUseCaseTest {

    @Mock
    ItemRepository repository;
    ItemGetByCategoryUseCase useCase;

    @BeforeEach
    void init(){  useCase = new ItemGetByCategoryUseCase(repository); }


    @Test
    @DisplayName("ItemGetItemsByCategoryUseCase")
    void get() {
        String category = "School";

        Mockito.when(repository.getItemsByCategory(category)).thenReturn(Flux.just(new Item(), new Item()));

        var execute = useCase.apply(category);

        StepVerifier.create(execute)
                .expectNextMatches(Objects::nonNull)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).getItemsByCategory(category);

    }

}