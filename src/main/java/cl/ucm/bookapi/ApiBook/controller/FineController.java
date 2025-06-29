package cl.ucm.bookapi.ApiBook.controller;


import cl.ucm.bookapi.ApiBook.entities.FineEntity;
import cl.ucm.bookapi.ApiBook.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fine")
public class FineController {

    @Autowired
    private FineService fineService;

    // lista dfe multas de un lector
    @GetMapping("/find/{email}")
    public ResponseEntity<List<FineEntity>> findFinesByUser(@PathVariable String email) {
        return ResponseEntity.ok(fineService.findByUserFk(email));
    }

    // ingresar multa a usuario
    @PostMapping("/new")
    public ResponseEntity<FineEntity> createFine(@RequestBody FineEntity fine) {
        fine.setState(true);  // cuando se crea es true siempre
        return ResponseEntity.ok(fineService.save(fine));
    }

    // actualizar estado de una multa
    @PutMapping("/state/{id}")
    public ResponseEntity<?> updateFineState(@PathVariable int id, @RequestBody boolean newState) {
        Optional<FineEntity> updated = fineService.updateState(id, newState);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
