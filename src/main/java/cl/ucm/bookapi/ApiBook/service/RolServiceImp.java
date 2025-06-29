package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.RolEntity;
import cl.ucm.bookapi.ApiBook.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RolServiceImp implements RolService{
    @Autowired
    private RolRepository repository;


    @Override
    public RolEntity save(RolEntity rol) {
        return repository.save(rol);
    }

    @Override
    public List<RolEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RolEntity> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<RolEntity> findByName(String name) {
        return Optional.ofNullable(repository.findByName(name));
    }
}
