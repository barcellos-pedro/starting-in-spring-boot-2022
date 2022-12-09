package com.amigoscode.greet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/greet")
public class Greet {
    // @RequestMapping(method = RequestMethod.GET, path = '/greet')
    @GetMapping // leaner way
    public GreetResponse greet() {
        var greet = "Hello world!";
        var languages = List.of("Javascript", "Java", "Kotlin");
        var person = new Person("Pedro", 25, true);
        return new GreetResponse(greet, languages, person);
    }

    @GetMapping("/names-list")
    public List<String> names() {
        return Arrays.asList("pedro", "ana", "paula");
    }

    @GetMapping("/names-search") // names-search?search=<input>
    public String search(@RequestParam(name = "search") String search) throws Exception {
        // Using streams API
        return Arrays.asList("Pedro", "Ana", "Toninho")
                .stream()
                .map(String::toLowerCase)
                .filter(name -> name.equals(search.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new Exception("Name not found"));
                // .filter(name -> name.endsWith(search)).collect(Collectors.toList()); // return type will be List<String>
    }

    record GreetResponse(String greet, List<String> languages, Person person) {
    }

    record Person(String name, int age, Boolean isAlive) {
    }
}
