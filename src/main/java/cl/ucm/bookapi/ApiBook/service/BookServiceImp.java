package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.BookEntity;
import cl.ucm.bookapi.ApiBook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    private BookRepository repository;

    @Override
    public BookEntity save(BookEntity book){
        return repository.save(book);
    }

    @Override
    public List<BookEntity> findAll(){
        return repository.findAll();
    }

    @Override
    public Optional<BookEntity> findByTitle(String title) {
        return Optional.ofNullable(repository.findByTitle(title));
    }

    @Override
    public List<BookEntity> findByType(String type) {
        return repository.findByType(type);
    }
}
