package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.BookEntity;
import cl.ucm.bookapi.ApiBook.entities.CopyBookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(BookEntity book);
    List<BookEntity> getAllBooks();
    List<BookEntity> getBooksByType(String type);
    List<BookEntity> getBooksByTitle(String title);
    CopyBookEntity createBookCopy(int bookId);
    List<CopyBookEntity> getAvailableCopiesByTitle(String title);
}
