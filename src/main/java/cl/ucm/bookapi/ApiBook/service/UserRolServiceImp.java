package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.UserRolEntity;
import cl.ucm.bookapi.ApiBook.repository.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolServiceImp implements UserRolService{
    @Autowired
    private UserRolRepository repository;


    @Override
    public UserRolEntity save(UserRolEntity userRol) {
        return repository.save(userRol);
    }

    @Override
    public List<UserRolEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserRolEntity> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<UserRolEntity> findByUserFk(String email) {
        return repository.findByUserFk(email);
    }
}
