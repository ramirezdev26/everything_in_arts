package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.model.item.gateways.ItemRepository;
import co.com.everything_in_arts.mongo.data.ItemData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterItem implements ItemRepository {


    private final MongoDBRepositoryItem repository;

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
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Flux<Item> getItemsByCategory(String category) {
        return this.repository.findByCategory(category)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("items with category: " + category + "was not found")))
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Item> saveItem(Item item) {
        return this.repository
                .save(mapper.map(item, ItemData.class))
                .switchIfEmpty(Mono.empty())
                .map(itemData -> mapper.map(itemData, Item.class));
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
    public Mono<String> deleteItem(String id) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("item with id: " + id + " was not found")))
                .flatMap(itemData -> this.repository.delete(itemData)).thenReturn(id);
    }

    @Override
    public Mono<String> decreaseStock(String id) {
        return this.repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("item with id: " + id + " was not found")))
                .flatMap(itemData -> {
                    itemData.reduceStock();
                    return repository.save(itemData);
                }).thenReturn(id);
    }


}
