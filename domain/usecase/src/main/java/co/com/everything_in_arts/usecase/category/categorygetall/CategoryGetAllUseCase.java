package co.com.everything_in_arts.usecase.category.categorygetall;

import co.com.everything_in_arts.model.category.Category;
import co.com.everything_in_arts.model.category.gateways.CategoryRepository;
import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class CategoryGetAllUseCase implements Supplier<Flux<Category>> {

    private final CategoryRepository repository;


    @Override
    public Flux<Category> get() {
        return repository.getAllCategory();
    }

}
