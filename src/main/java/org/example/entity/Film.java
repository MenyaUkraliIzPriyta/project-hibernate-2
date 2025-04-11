package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.Rating;
import org.example.utils.RatingAttributeConverter;
import org.example.utils.SpecialFeaturesAttributeConverter;
import org.example.utils.YearAttributeConverter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false)
    private Long filmId;

    @Column(name = "title", length = 128)
    private String title;

    @Column(name = "description")
    private String description;

    @Convert(converter = YearAttributeConverter.class)
    @Column(name = "release_year")
    private Year year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id", nullable = false)
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Convert(converter = RatingAttributeConverter.class)
    @Column(name = "rating")
    private Rating rating;

    @Convert(converter = SpecialFeaturesAttributeConverter.class)
    @Column(name = "special_features")
    private Set<String> specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "film_actor", // имя таблицы-связки
            joinColumns = @JoinColumn(name = "film_id"), // внешний ключ на Student
            inverseJoinColumns = @JoinColumn(name = "actor_id") // внешний ключ на Course
    )
    private List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "film_category", // имя таблицы-связки
            joinColumns = @JoinColumn(name = "film_id"), // внешний ключ на Student
            inverseJoinColumns = @JoinColumn(name = "category_id") // внешний ключ на Course
    )
    private List<Category> categories;

    @OneToOne(mappedBy = "film")
    private FilmText filmText;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Inventory> inventory;
}
