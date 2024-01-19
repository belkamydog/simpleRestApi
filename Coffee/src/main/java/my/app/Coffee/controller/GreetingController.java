package my.app.Coffee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @Value("${greeting-name:User}")
    private String name;

    @Value("${greeting-coffee: ${greeting-name} do you want a cup of coffee?}")
    private String coffee;

    @GetMapping
    public String getName(){
        return name;
    }

    @GetMapping("/coffee")
    public String greetingAndCoffee(){
        return coffee;
    }
}
