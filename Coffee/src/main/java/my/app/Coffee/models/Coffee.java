package my.app.Coffee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class Coffee {
    private Long id;
    private String name;
}
