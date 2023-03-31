package co.com.everything_in_arts.mongo.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;
import java.util.function.Function;

import static org.springframework.data.domain.Example.of;

@Data
@Document(collection = "item")
@NoArgsConstructor
public class ItemData {

    @Id
    private String id;

    @NotNull(message = "category  can't be null")
    @NotBlank(message = "category can't be empty")
    private String category;

    @NotNull(message = "description  can't be null")
    @NotBlank(message = "description can't be empty")
    private String description;

    @NotNull(message = "name  can't be null")
    @NotBlank(message = "name can't be empty")
    private String name;

    @NotNull(message = "price  can't be null")
    private Integer price;

    @NotNull(message = "stock  can't be null")
    private Integer stock;



    public ItemData(String category, String description, String name, Integer price, Integer stock) {
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.category = category;
        this.description = description;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

}
