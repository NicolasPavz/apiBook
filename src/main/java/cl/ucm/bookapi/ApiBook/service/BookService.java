package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(BookEntity book);
    List<BookEntity> getAllBooks();
    List<BookEntity> getBooksByType(String type);
    List<BookEntity> getBooksByTitle(String title);
}
