package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.UserEntity;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return repository.findById(email);
    }

    @Override
    public Optional<UserEntity> updateState(String email, boolean state) {
        Optional<UserEntity> optional = repository.findById(email);
        if(optional.isPresent()){
            UserEntity user = optional.get();
            user.setState(state);
            return Optional.of(repository.save(user));
        }
        return Optional.empty();
    }
}
