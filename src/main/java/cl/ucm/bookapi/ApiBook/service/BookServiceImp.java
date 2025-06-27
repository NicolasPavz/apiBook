package cl.ucm.bookapi.ApiBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CopybookRepository copybookRepository;


}
