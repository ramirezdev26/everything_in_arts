package co.com.everything_in_arts.usecase.itemgetall;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@AllArgsConstructor
public class ItemGetAllUseCase implements Supplier<Flux<Item>> {

    private final ItemRepository repository;


    @Override
    public Flux<Item> get() {
        return repository.getAllItems();
    }
}
