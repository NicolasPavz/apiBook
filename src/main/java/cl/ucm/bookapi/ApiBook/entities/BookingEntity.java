package cl.ucm.bookapi.ApiBook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "booking")

public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private int id;
    @Column(name = "copybook_fk")
    private int copyBookFk;
    @Column(name = "date_booking")
    private LocalDateTime dateBooking;
    @Column(name = "date_return")
    private LocalDateTime dateReturn;
    private boolean state;
    @Column(name = "user_fk")
    private String userFk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk", referencedColumnName = "email", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copybook_fk", referencedColumnName = "id_copybook", insertable = false, updatable = false)
    private CopyBookEntity copyBook;
}
