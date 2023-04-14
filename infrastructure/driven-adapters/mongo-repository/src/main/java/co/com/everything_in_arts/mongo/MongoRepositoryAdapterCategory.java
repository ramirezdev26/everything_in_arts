package co.com.everything_in_arts.mongo;

import co.com.everything_in_arts.model.category.Category;
import co.com.everything_in_arts.model.category.gateways.CategoryRepository;
import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterCategory implements CategoryRepository {

    private final MongoDBRepositoryCategory repository;

    private final ObjectMapper mapper;

    @Override
    public Flux<Category> getAllCategory() {
        return this.repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(categoryData -> mapper.map(categoryData, Category.class));
    }
}
