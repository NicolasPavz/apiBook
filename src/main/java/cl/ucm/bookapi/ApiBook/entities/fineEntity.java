package cl.ucm.bookapi.ApiBook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "fine")

public class fineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fine")
    private int id;
    private int amount;
    private String description;
    private boolean state;
    @Column(name = "user_fk")
    private String userFk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk", referencedColumnName = "email", insertable = false, updatable = false)
    private userEntity user;
}
