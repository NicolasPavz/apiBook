package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.FineEntity;
import cl.ucm.bookapi.ApiBook.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FineServiceImp implements FineService{
    @Autowired
    private FineRepository repository;

    @Override
    public FineEntity save(FineEntity fine) {
        return repository.save(fine);
    }

    @Override
    public List<FineEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<FineEntity> findByUserFk(String email) {
        return repository.findByUserFk(email);
    }

    @Override
    public Optional<FineEntity> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<FineEntity> updateState(int id, boolean state) {
        Optional<FineEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            FineEntity fine = optional.get();
            fine.setState(state);
            return Optional.of(repository.save(fine));
        }
        return Optional.empty();
    }
}
