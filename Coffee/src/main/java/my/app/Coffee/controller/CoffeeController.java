package my.app.Coffee.controller;

import my.app.Coffee.models.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final List<Coffee> coffeeList;
    public CoffeeController(){
        coffeeList = new ArrayList<>();
        long startID = 0L;
        coffeeList.add(new Coffee(++startID, "Cappuccino"));
        coffeeList.add(new Coffee(++startID, "Espresso"));
        coffeeList.add(new Coffee(++startID, "Latte"));
        coffeeList.add(new Coffee(++startID, "Cold brew"));

    }

    //Аннотация @RequestMapping предназначена для того, чтобы задать методам вашего контроллера адреса,
    // по которым они будут доступны на клиенте.
    @GetMapping
    public List<Coffee> getCoffeeList(){
        return coffeeList;
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffee(@PathVariable Long id){
        for (Coffee i : coffeeList){
            if (i.getId().equals(id)) return Optional.of(i);
        }
        return Optional.empty();
    }

    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
        coffeeList.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    public ResponseEntity <Coffee> putCoffee(@RequestBody Coffee coffee){
        boolean isFound = false;
        for (Coffee i: coffeeList){
            if (i.equals(coffee)){
                i.setId(coffee.getId());
                i.setName(coffee.getName());
                isFound = true;
            }
            else coffeeList.add(coffee);
        }
        return (isFound) ? new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED):
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id){
        coffeeList.removeIf(coffee -> coffee.getId().equals(id));
    }
}