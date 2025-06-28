package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.BookingEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingEntity save(BookingEntity booking);
    List<BookingEntity> findAll();
    Optional<BookingEntity> findById(int id);
    List<BookingEntity> findByUserFk(String email);
    Optional<BookingEntity> updateStateAndReturnDate(int id, boolean state, LocalDateTime dateReturn);
}
