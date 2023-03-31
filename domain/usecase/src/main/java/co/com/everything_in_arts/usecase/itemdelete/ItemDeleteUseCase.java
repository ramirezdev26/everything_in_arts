package co.com.everything_in_arts.usecase.itemdelete;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ItemDeleteUseCase implements Function<String, Mono<Void>> {

    private final ItemRepository repository;


    @Override
    public Mono<Void> apply(String s) {
        return repository.deleteItem(s);
    }
}
