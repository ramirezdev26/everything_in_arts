package co.com.everything_in_arts.model.invoice;
import co.com.everything_in_arts.model.item.Item;
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
public class Invoice {

    private String id;
    private Set<Item> itemList;
    private String date;
    private String userName;
    private String email;
    private Integer total;

}
