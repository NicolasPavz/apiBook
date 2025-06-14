package cl.ucm.bookapi.ApiBook.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class userEntity {
    @Id
    private String email;
    @Column(name = "last_name")
    private String lastName;
    private String name;
    private String password;
    private boolean state;

}
