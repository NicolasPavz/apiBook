package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.BookingEntity;
import cl.ucm.bookapi.ApiBook.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BookingServiceImp implements BookingService{
    @Autowired
    private BookingRepository repository;


    @Override
    public BookingEntity save(BookingEntity booking) {
        return repository.save(booking);
    }

    @Override
    public List<BookingEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BookingEntity> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<BookingEntity> findByUserFk(String email) {
        return repository.findByUserFk(email);
    }

    @Override
    public Optional<BookingEntity> updateStateAndReturnDate(int id, boolean state, LocalDateTime dateReturn) {
        Optional<BookingEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            BookingEntity booking = optional.get();
            booking.setState(state);
            booking.setDateReturn(dateReturn);
            return Optional.of(repository.save(booking));
        }
        return Optional.empty();
    }
}
