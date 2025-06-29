package cl.ucm.bookapi.ApiBook.controller;

import cl.ucm.bookapi.ApiBook.entities.BookEntity;
import cl.ucm.bookapi.ApiBook.entities.CopyBookEntity;
import cl.ucm.bookapi.ApiBook.service.BookService;
import cl.ucm.bookapi.ApiBook.service.CopyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/book")
public class CopyBookController {

    @Autowired
    private CopyBookService copyService;

    @Autowired
    private BookService bookService;

    // ingresar copia de libro
    @PostMapping("/newcopy")
    public ResponseEntity<?> saveCopy(@RequestBody CopyBookEntity copy) {
        CopyBookEntity saved = copyService.save(copy);
        return ResponseEntity.ok(saved);
    }

    // copias disponibles
    @GetMapping("/copy/{title}")
    public ResponseEntity<?> getAvailableCopies(@PathVariable String title) {
        //se busca el libro por el titulo para ver si existe
        Optional<BookEntity> optBook = bookService.findByTitle(title);
        if (optBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // sew obtienen las copias disponibles (state = true) usando el id del libro
        int bookId = optBook.get().getId();
        List<CopyBookEntity> copies = copyService.findAvailableByBookFk(bookId);

        return ResponseEntity.ok(copies);
    }
}

