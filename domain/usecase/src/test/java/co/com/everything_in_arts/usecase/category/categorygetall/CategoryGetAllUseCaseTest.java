package co.com.everything_in_arts.usecase.category.categorygetall;

import co.com.everything_in_arts.model.category.Category;
import co.com.everything_in_arts.model.category.gateways.CategoryRepository;
import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.model.invoice.gateways.InvoiceRepository;
import co.com.everything_in_arts.usecase.invoice.invoicegetall.InvoiceGetAllUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryGetAllUseCaseTest {

    @Mock
    CategoryRepository repository;
    CategoryGetAllUseCase useCase;

    @BeforeEach
    void init(){  useCase = new CategoryGetAllUseCase(repository); }

    @Test
    @DisplayName("CategoryGetAllUseCase")
    void get() {

        Mockito.when(repository.getAllCategory()).thenReturn(Flux.just(new Category(), new Category()));

        var execute = useCase.get();

        StepVerifier.create(execute)
                .expectNextMatches(Objects::nonNull)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).getAllCategory();

    }

}