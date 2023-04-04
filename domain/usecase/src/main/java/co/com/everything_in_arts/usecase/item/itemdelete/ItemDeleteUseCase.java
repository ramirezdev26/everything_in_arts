package co.com.everything_in_arts.usecase.item.itemdelete;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ItemDeleteUseCase implements Function<String, Mono<String>> {

    private final ItemRepository repository;


    @Override
    public Mono<String> apply(String id) {
        return repository.deleteItem(id);
    }
}
