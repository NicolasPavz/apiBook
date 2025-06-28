package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity book);
    List<BookEntity> findAll();
    Optional<BookEntity> findByTitle(String title);
    List<BookEntity> findByType(String type);
}
