package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.CopyBookEntity;
import cl.ucm.bookapi.ApiBook.repository.CopyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyBookServiceImp implements CopyBookService{
    @Autowired
    private CopyBookRepository repository;


    @Override
    public CopyBookEntity save(CopyBookEntity copyBook) {
        copyBook.setState(true);
        return repository.save(copyBook);
    }

    @Override
    public List<CopyBookEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CopyBookEntity> findAvailableByBookFk(int bookFk) {
        return repository.findAvailableByBookFk(bookFk);
    }

    @Override
    public Optional<CopyBookEntity> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<CopyBookEntity> updateState(int id, boolean state) {
        Optional<CopyBookEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            CopyBookEntity entity = optional.get();
            entity.setState(state);
            return Optional.of(repository.save(entity));
        }
        return Optional.empty();
    }
}
