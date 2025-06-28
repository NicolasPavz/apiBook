package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.CopyBookEntity;

import java.util.List;
import java.util.Optional;

public interface CopyBookService {
    CopyBookEntity save(CopyBookEntity copyBook);
    List<CopyBookEntity> findAll();
    List<CopyBookEntity> findAvailableByBookFk(int bookFk);
    Optional<CopyBookEntity> findById(int id);
    Optional<CopyBookEntity> updateState(int id, boolean state);
}
