package co.com.everything_in_arts.mongo.data;

import co.com.everything_in_arts.model.item.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Document(collection = "invoice")
@NoArgsConstructor
public class InvoiceData {

    @Id
    private String id;

    private Set<Item> itemList;

    private Date date = new Date();
    @NotNull(message = "userName  can't be null")
    @NotBlank(message = "userName can't be empty")
    private String name;
    @NotNull(message = "email  can't be null")
    @NotBlank(message = "email can't be empty")
    private String email;
    @NotNull(message = "total  can't be null")
    private Integer total;

    @NotNull(message = "email  can't be null")
    @NotBlank(message = "email can't be empty")
    private String address;

    public InvoiceData(String name, String email, Integer total, Set<Item> itemList, String address) {
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.itemList = itemList;
        this.date = new Date();
        this.name = name;
        this.email = email;
        this.total = total;
        this.address = address;
    }
}
