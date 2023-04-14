package co.com.everything_in_arts.model.item.gateways;

import co.com.everything_in_arts.model.item.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface ItemRepository {

    Flux<Item> getAllItems();
    Mono<Item> getItemById(String id);
    Flux<Item> getItemsByCategory(String category);
    Mono<Item> saveItem(Item item);
    Mono<Item> updateItem(String id, Item item);
    Mono<String> deleteItem(String id);
    Mono<String> decreaseStock(String idList);

}
