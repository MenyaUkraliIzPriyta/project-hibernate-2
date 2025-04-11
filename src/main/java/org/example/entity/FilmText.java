package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "film_text")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}
