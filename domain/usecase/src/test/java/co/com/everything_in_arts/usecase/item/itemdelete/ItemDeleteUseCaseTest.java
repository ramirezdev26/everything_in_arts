package co.com.everything_in_arts.usecase.item.itemdelete;

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
class ItemDeleteUseCaseTest {

    @Mock
    ItemRepository repository;
    ItemDeleteUseCase useCase;

    @BeforeEach
    void init(){  useCase = new ItemDeleteUseCase(repository); }


    @Test
    @DisplayName("ItemDeleteUseCase")
    void apply() {
        String id = "642c32a2470b822002324c83";

        Mockito.when(repository.deleteItem(id)).thenReturn(Mono.just(id));

        var execute = useCase.apply(id);

        StepVerifier.create(execute)
                .expectNext(id)
                .verifyComplete();

        Mockito.verify(repository).deleteItem(id);

    }
}