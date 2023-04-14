package co.com.everything_in_arts.mongo.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "category")
@NoArgsConstructor
public class CategoryData {

    @Id
    private String id;

    @NotNull(message = "category  can't be null")
    @NotBlank(message = "category can't be empty")
    private String category;

    public CategoryData(String category) {
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.category = category;
    }
}
