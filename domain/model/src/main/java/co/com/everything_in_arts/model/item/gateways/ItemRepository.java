package co.com.everything_in_arts.model.item.gateways;

import co.com.everything_in_arts.model.item.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemRepository {

    Flux<Item> getAllItems();
    Mono<Item> getItemById(String id);
    Mono<Item> saveItem(Item item);
    Mono<Item> updateItem(String id, Item item);
    Mono<Void> deleteItem(String id);

}
