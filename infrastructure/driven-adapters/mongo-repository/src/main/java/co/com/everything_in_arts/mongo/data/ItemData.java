package co.com.everything_in_arts.mongo.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

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

    @NotNull(message = "images  can't be null")
    private Set<String> images;



    public ItemData(String category, String description, String name, Integer price, Integer stock, Set<String> images) {
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.category = category;
        this.description = description;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.images = images;
    }

    public void reduceStock(){
        this.stock -= 1;
    }

}
