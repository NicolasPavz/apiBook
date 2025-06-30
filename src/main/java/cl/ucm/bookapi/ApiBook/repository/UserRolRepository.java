package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.entities.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity,Integer> {
    @Query(value = "select r.name from rol r inner join user_rol ur on ur.rol_fk=r.id_rol inner join user u on ur.user_fk=u.email  where u.email=:username", nativeQuery = true)
    List<String> getRolesByUsername(String username);
    List<UserRolEntity> findByUserFk(String email);
}
