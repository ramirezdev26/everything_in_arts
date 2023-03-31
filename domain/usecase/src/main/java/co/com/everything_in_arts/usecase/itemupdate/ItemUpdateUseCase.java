package co.com.everything_in_arts.usecase.itemupdate;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class ItemUpdateUseCase implements BiFunction<String, Item, Mono<Item>> {

    private final ItemRepository repository;

    @Override
    public Mono<Item> apply(String id, Item item) {
        return repository.updateItem(id, item);
    }
}
