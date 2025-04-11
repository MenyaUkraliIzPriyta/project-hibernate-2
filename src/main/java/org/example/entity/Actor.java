package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "actor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    private Long actorId;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name",length = 45)
    private String lastName;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "film_actor", // имя таблицы-связки
            joinColumns = @JoinColumn(name = "actor_id"), // внешний ключ на Student
            inverseJoinColumns = @JoinColumn(name = "film_id") // внешний ключ на Course
    )
    private List<Film> film;


}
