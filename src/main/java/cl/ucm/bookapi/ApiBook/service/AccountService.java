package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.dto.in.RegisterDto;
import cl.ucm.bookapi.ApiBook.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    UserEntity save(UserEntity user);
    List<UserEntity> findAll();
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> updateState(String email, boolean state);
    Optional<RegisterDto> createUser(RegisterDto dto);
}
