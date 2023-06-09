package co.com.everything_in_arts.model.item;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private Set<String> images;


}
