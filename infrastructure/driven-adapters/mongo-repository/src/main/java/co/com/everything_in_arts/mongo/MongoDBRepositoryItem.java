package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.mongo.data.ItemData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryItem extends ReactiveMongoRepository<ItemData, String> {
}
