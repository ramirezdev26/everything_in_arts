package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.mongo.data.InvoiceData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryInvoice extends ReactiveMongoRepository<InvoiceData, String> {
}
