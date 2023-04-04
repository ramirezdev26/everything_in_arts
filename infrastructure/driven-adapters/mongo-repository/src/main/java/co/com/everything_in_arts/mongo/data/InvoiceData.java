package co.com.everything_in_arts.mongo.data;

import co.com.everything_in_arts.model.item.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Document(collection = "invoice")
@NoArgsConstructor
public class InvoiceData {

    @Id
    private String id;

    private Set<Item> itemList = new HashSet<>();;
    @NotNull(message = "date  can't be null")
    @NotBlank(message = "date can't be empty")
    private String date;
    @NotNull(message = "userName  can't be null")
    @NotBlank(message = "userName can't be empty")
    private String userName;
    @NotNull(message = "email  can't be null")
    @NotBlank(message = "email can't be empty")
    private String email;
    @NotNull(message = "total  can't be null")
    private Integer total;

    public InvoiceData(String date, String userName, String email, Integer total) {
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.itemList = new HashSet<>();
        this.date = date;
        this.userName = userName;
        this.email = email;
        this.total = total;
    }
}
