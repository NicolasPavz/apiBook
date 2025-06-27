package cl.ucm.bookapi.ApiBook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_rol")
public class UserRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_user")
    private int id;
    @Column(name = "rol_fk")
    private int rolFk;
    @Column(name = "user_fk")
    private String userFk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_fk", referencedColumnName = "id_rol", insertable = false, updatable = false)
    private RolEntity rol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk", referencedColumnName = "email", insertable = false, updatable = false)
    private UserEntity user;
}
