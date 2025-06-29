package cl.ucm.bookapi.ApiBook.service;

import cl.ucm.bookapi.ApiBook.entities.FineEntity;

import java.util.List;
import java.util.Optional;

public interface FineService {
    FineEntity save(FineEntity fine);
    List<FineEntity> findAll();
    List<FineEntity> findByUserFk(String email);
    Optional<FineEntity> findById(int id);
    Optional<FineEntity> updateState(int id, boolean state);
}
