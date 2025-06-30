package cl.ucm.bookapi.ApiBook.controller;

import cl.ucm.bookapi.ApiBook.entities.BookingEntity;
import cl.ucm.bookapi.ApiBook.entities.CopyBookEntity;
import cl.ucm.bookapi.ApiBook.entities.UserEntity;
import cl.ucm.bookapi.ApiBook.service.BookingService;
import cl.ucm.bookapi.ApiBook.service.CopyBookService;
import cl.ucm.bookapi.ApiBook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// http://localhost:8087/api/booking
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private CopyBookService copyBookService;

    @Autowired
    private AccountService accountService;

    // Registrar prestamo de una copuia
    @PostMapping("/new")
    public ResponseEntity<?> createBooking(@RequestBody BookingEntity booking) {
        // se valida el usuario
        Optional<UserEntity> optUser = accountService.findByEmail(booking.getUserFk());
        if (optUser.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        // estado del usuario
        if (!optUser.get().isState()) {
            return ResponseEntity.badRequest().body("Usuario inactivo o multado");
        }

        // cantidad de prestamos del usuario < 3
        List<BookingEntity> activeBookings = bookingService.findByUserFk(booking.getUserFk());
        long countActive = activeBookings.stream().filter(BookingEntity::isState).count();
        if (countActive >= 3) {
            return ResponseEntity.badRequest().body("Máximo de 3 préstamos activos alcanzado");
        }

        // disponibilidad de la copia
        Optional<CopyBookEntity> optCopy = copyBookService.findById(booking.getCopyBookFk());
        if (optCopy.isEmpty() || !optCopy.get().isState()) {
            return ResponseEntity.badRequest().body("Copia del libro no disponible");
        }

        // fecha de reserva y fecha de devolución 5 dias
        booking.setDateBooking(LocalDateTime.now());
        booking.setState(true);
        booking.setDateReturn(LocalDateTime.now().plusDays(5));

        // se guarda el prestamo
        BookingEntity saved = bookingService.save(booking);

        // estado de la copia a false
        copyBookService.updateState(booking.getCopyBookFk(), false);

        return ResponseEntity.ok(saved);
    }

    // prestamos de una usuario segun email
    @GetMapping("/find/{email}")
    public ResponseEntity<List<BookingEntity>> getBookingsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(bookingService.findByUserFk(email));
    }


    // retorno de una reserva
    @PostMapping("/return/{idBooking}")
    public ResponseEntity<?> returnBook(@PathVariable int idBooking) {

        // se verifica la existencia de la reserva
        Optional<BookingEntity> optBooking = bookingService.findById(idBooking);
        if (optBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BookingEntity booking = optBooking.get();

        // actualiza el estado de la reserva y fecha de devolucion
        bookingService.updateStateAndReturnDate(idBooking, false, LocalDateTime.now());

        // Liberar la copia del libro
        copyBookService.updateState(booking.getCopyBookFk(), true);

        return ResponseEntity.ok("Libro devuelto con éxito");
    }
}
