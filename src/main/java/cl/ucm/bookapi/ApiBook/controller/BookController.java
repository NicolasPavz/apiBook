package cl.ucm.bookapi.ApiBook.controller;


import cl.ucm.bookapi.ApiBook.entities.BookEntity;
import cl.ucm.bookapi.ApiBook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// http://localhost:8087/api/book

@RestController
@RequestMapping(path = "/api/book")
public class BookController {
    @Autowired
    private BookService service;

    // Libros disponibles
    @GetMapping("/all")
    public ResponseEntity<?> findAllBooks(){
        return ResponseEntity.ok(service.findAll());
    }

    //libros po tipo
    @GetMapping("/all/{type}")
    public ResponseEntity<?> findByType(@PathVariable String type){
        return ResponseEntity.ok(service.findByType(type));
    }

    // buscar por titulo
    @GetMapping("/find/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable String title){
        Optional<BookEntity> optional = service.findByTitle(title);
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();

    }

    //Registrar libro
    @PostMapping("/new")
    public ResponseEntity<?> save(@RequestBody BookEntity book){
        return ResponseEntity.ok(service.save(book));
    }


}
