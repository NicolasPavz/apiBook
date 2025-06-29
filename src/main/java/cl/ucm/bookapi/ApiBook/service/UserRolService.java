package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.UserRolEntity;

import java.util.List;
import java.util.Optional;

public interface UserRolService{
    UserRolEntity save(UserRolEntity userRol);
    List<UserRolEntity> findAll();
    Optional<UserRolEntity> findById(int id);
    List<UserRolEntity> findByUserFk(String email);
}
