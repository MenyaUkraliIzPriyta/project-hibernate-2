package org.example.repository;

import org.example.entity.Film;

public class FilmRepository extends FoundationReposirory<Film> {
    public FilmRepository() {
        super(Film.class);
    }
}
