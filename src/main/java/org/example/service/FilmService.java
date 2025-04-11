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

    public Film newFilmWasReleased(String title, String releaseYear,
                                   Integer rentalDuration, BigDecimal rentalRate,
                                   Integer length, BigDecimal rentalCost,
                                   Rating rating, Set<String> specialsFeatures) {
        txManager.execute(session -> {
            Language language = languageRepository.findById(session, 1L);
            Language originalLanguage = languageRepository.findById(session, 4L);
            List<Category> categories = session.createQuery("select c from Category c",Category.class).setMaxResults(3).list();
            List<Actor> actors = session.createQuery("select a from Actor a", Actor.class).setMaxResults(52).list();

            Film film = Film.builder()
                    .language(language)
                    .categories(categories)
                    .actors(actors)
                    .rating(Rating.PG)
                    .specialFeatures("Commentaries,Deleted Scenes,Behind the Scenes")
                    .length((short) 100)
                    .replacementCost(BigDecimal.ZERO)
                    .rentalRate(BigDecimal.ZERO)
                    .description("wow!")
                    .title("Movie")
                    .rentalDuration((byte) 52)
                    .originalLanguage(language)
                    .year(Year.now())
                    .build();
            session.persist(film);

            return null;
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
