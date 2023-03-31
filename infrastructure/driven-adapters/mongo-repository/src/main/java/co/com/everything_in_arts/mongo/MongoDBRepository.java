package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.mongo.data.ItemData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<ItemData, String> {
}
