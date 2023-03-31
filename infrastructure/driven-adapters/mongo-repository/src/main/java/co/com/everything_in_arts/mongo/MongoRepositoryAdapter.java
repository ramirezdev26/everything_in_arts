package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import co.com.everything_in_arts.mongo.data.ItemData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapter implements ItemRepository {


    private final MongoDBRepository repository;

    private final ObjectMapper mapper;

    @Override
    public Flux<Item> getAllItems() {
        return this.repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Item> getItemById(String id) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("item with id: " + id + "was not found")))
                .map(flowerData -> mapper.map(flowerData, Item.class));
    }

    @Override
    public Mono<Item> saveItem(Item item) {
        return this.repository
                .save(mapper.map(item, ItemData.class))
                .switchIfEmpty(Mono.empty())
                .map(flowerData -> mapper.map(flowerData, Item.class));
    }

    @Override
    public Mono<Item> updateItem(String id, Item item) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("item with id: " + id + " was not found")))
                .flatMap(itemData -> {
                    item.setId(itemData.getId());
                    return repository.save(mapper.map(item, ItemData.class));
                })
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Void> deleteItem(String id) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("item with id: " + id + " was not found")))
                .flatMap(itemData -> this.repository.deleteById(itemData.getId()));
    }
}
