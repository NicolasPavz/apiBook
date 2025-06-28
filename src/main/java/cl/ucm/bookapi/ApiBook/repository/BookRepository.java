package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
    BookEntity findByTitle(String title);

    @Query(value = "SELECT * FROM book WHERE type = :type", nativeQuery = true)
    List<BookEntity> findByType(String type);
}
