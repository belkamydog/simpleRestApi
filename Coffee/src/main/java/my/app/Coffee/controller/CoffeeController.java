package my.app.Coffee.controller;

import my.app.Coffee.models.Coffee;
import my.app.Coffee.repository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;
    public CoffeeController(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
        coffeeRepository.save(new Coffee(0L, "Americano"));
        coffeeRepository.save(new Coffee(0L, "Espresso"));
        coffeeRepository.save(new Coffee(0L, "Cappuccino"));
    }

    //Аннотация @RequestMapping предназначена для того, чтобы задать методам вашего контроллера адреса,
    // по которым они будут доступны на клиенте.
    @GetMapping
    public List<Coffee> getCoffeeList(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffee(@PathVariable Long id){
        return coffeeRepository.findById(id);
    }

    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Coffee> putCoffee(@PathVariable Long id, @RequestBody Coffee coffee){
        return (coffeeRepository.existsById(id)) ? new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED):
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id){
        coffeeRepository.deleteById(id);
    }
}