package co.com.everything_in_arts.usecase.item.itemupdate;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
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
class ItemUpdateUseCaseTest {

    @Mock
    ItemRepository repository;
    ItemUpdateUseCase useCase;

    @BeforeEach
    void init(){  useCase = new ItemUpdateUseCase(repository); }


    @Test
    @DisplayName("ItemUpdateUseCase")
    void apply() {
        String id = "642c32a2470b822002324c83";
        Item item = new Item();

        Mockito.when(repository.updateItem(id ,item)).thenReturn(Mono.just(item));

        var execute = useCase.apply(id ,item);

        StepVerifier.create(execute)
                .expectNext(item)
                .verifyComplete();

        Mockito.verify(repository).updateItem(id ,item);

    }

}