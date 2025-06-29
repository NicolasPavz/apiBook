package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.RolEntity;

import java.util.List;
import java.util.Optional;

public interface RolService {
    RolEntity save(RolEntity rol);
    List<RolEntity> findAll();
    Optional<RolEntity> findById(int id);
    Optional<RolEntity> findByName(String name);
}
