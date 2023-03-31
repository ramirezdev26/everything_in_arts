package co.com.everything_in_arts.model.item;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Item {

    private String id;

    private String category;

    private String description;

    private String name;

    private Integer price;
    private Integer stock;


}
