package co.com.everything_in_arts.usecase.item.itemsave;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ItemSaveUseCase implements Function<Item, Mono<Item>> {

    private final ItemRepository repository;

    @Override
    public Mono<Item> apply(Item item) {
        return repository.saveItem(item);
    }
}
