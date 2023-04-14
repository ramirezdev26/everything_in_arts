package co.com.everything_in_arts.usecase.item.itemdecreasestock;

import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ItemDecreaseStockUseCase implements Function<String, Mono<String>> {

    private final ItemRepository repository;


    @Override
    public Mono<String> apply(String id) {
        return repository.decreaseStock(id);
    }

}


