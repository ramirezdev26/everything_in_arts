package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.mongo.data.ItemData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MongoDBRepositoryItem extends ReactiveMongoRepository<ItemData, String> {

    Flux<ItemData> findByCategory(String category);

}
