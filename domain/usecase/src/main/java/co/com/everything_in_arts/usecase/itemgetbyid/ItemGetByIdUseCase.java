package co.com.everything_in_arts.usecase.itemgetbyid;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ItemGetByIdUseCase implements Function<String, Mono<Item>> {

    private final ItemRepository repository;

    public Mono<Item> apply(String id){
        return repository.getItemById(id);
    }


}
