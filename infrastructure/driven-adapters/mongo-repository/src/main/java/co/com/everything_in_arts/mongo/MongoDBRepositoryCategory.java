package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.mongo.data.CategoryData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryCategory extends ReactiveMongoRepository<CategoryData, String> {
}
