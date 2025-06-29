package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.entities.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity,Integer> {
    List<UserRolEntity> findByUserFk(String email);
}
