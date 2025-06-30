package cl.ucm.bookapi.ApiBook.controller;

import cl.ucm.bookapi.ApiBook.entities.UserEntity;
import cl.ucm.bookapi.ApiBook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// http://localhost:8087/api/reader
@RestController
@RequestMapping("/api/reader")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // buscart lector por id
    @GetMapping("/find/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        Optional<UserEntity> optional = accountService.findByEmail(email);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        return ResponseEntity.ok(accountService.findAll());
    }

    // cambiar estado de usuario
    @PostMapping("/state/{email}")
    public ResponseEntity<?> updateUserState(@PathVariable String email, @RequestBody boolean newState) {
        Optional<UserEntity> optional = accountService.updateState(email, newState);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
