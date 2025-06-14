package cl.ucm.bookapi.ApiBook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name="copy_book")
public class copyBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_copybook")
    private int Id;
    @Column(name="book_fk") //Fk de book
    private int bookFk;
    private boolean state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_fk", referencedColumnName = "book_id", insertable = false, updatable = false)
    private bookEntity book;
}
