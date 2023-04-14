package co.com.everything_in_arts.usecase.item.itemgetByCategory;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class ItemGetByCategoryUseCase implements Function<String, Flux<Item>> {

    private final ItemRepository repository;

    @Override
    public Flux<Item> apply( String category ){
        return repository.getItemsByCategory(category);
    }

}
