package my.app.Coffee.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "greeting")
public class Greeting {
    private String name;
    private String coffee;

}
