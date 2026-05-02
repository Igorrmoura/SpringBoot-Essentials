package br.com.Igor.spring_boot_essentials.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/hello")
public class HelloWordController {

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> get1(@PathVariable("id") String id) {
       return new ResponseEntity("helloWord" + id , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> get2(@RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity("helloWord" + name , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> post() {
        return new ResponseEntity("helloWord", HttpStatus.CREATED);
    }
}
