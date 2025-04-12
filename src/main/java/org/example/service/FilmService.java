package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.*;
import org.example.enums.Rating;
import org.example.repository.FilmRepository;
import org.example.repository.LanguageRepository;
import org.example.utils.TransactionManager;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class FilmService {
    private FilmRepository filmRepository;
    private TransactionManager txManager;
    private LanguageRepository languageRepository;

    public Film newFilmWasReleased(String title, Year releaseYear,
                                   Integer rentalDuration, BigDecimal rentalRate,
                                   Integer length, BigDecimal rentalCost,
                                   Rating rating) {
        txManager.execute(session -> {
            Language language = languageRepository.findById(session, 1L);
            Language originalLanguage = languageRepository.findById(session, 4L);
            List<Category> categories = session.createQuery("select c from Category c",Category.class).setMaxResults(3).list();
            List<Actor> actors = session.createQuery("select a from Actor a", Actor.class).setMaxResults(52).list();

            Film film = Film.builder()
                    .language(language)
                    .categories(categories)
                    .actors(actors)
                    .rating(rating)
                    .length(100)
                    .replacementCost(BigDecimal.ZERO)
                    .rentalRate(BigDecimal.ZERO)
                    .description("Amazing")
                    .title(title)
                    .rentalDuration(52)
                    .originalLanguage(language)
                    .year(Year.now())
                    .build();
            session.persist(film);

            FilmText filmText = FilmText.builder()
                    .film(film)
                    .description("wow!")
                    .title("Movie")
                    .build();
            session.persist(filmText);
            return film;
        });
        return null;
    }
    public Film findById(Long id) {
        return txManager.execute(session -> filmRepository.findById(session, id));
    }

    public List<Film> findAll() {
        return txManager.execute(filmRepository::findAll);
    }

    public void delete(Film film) {
        txManager.execute(session -> {
            filmRepository.delete(session, film);
            return null;
        });
    }

    public void save(Film film) {
        txManager.execute(session -> {
            filmRepository.save(session, film);
            return null;
        });
    }

    public void update(Film film) {
        txManager.execute(session -> {
            filmRepository.update(session, film);
            return null;
        });
    }
}
