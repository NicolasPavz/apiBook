package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.entities.CopyBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyBookRepository extends JpaRepository<CopyBookEntity, Integer> {
    @Query(value = "SELECT * FROM copy_book WHERE book_fk = :bookFk AND state = true", nativeQuery = true)
    List<CopyBookEntity> findAvailableByBookFk(int bookFk);
}
