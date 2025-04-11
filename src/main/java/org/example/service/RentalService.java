package org.example.service;

import lombok.AllArgsConstructor;

import org.example.entity.Rental;
import org.example.entity.Store;
import org.example.repository.RentalRepository;
import org.example.utils.TransactionManager;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final TransactionManager txManager;

    public Long returnFilm() {
        return txManager.execute(session -> {
            Rental rental = rentalRepository.createQueryList(
                    session, Rental.class, "select r from Rental r where r.returnDate is null").get(0);
            rental.setRentalDate(LocalDateTime.now());
            rentalRepository.update(session, rental);
            return rental.getRentalId();
        });

    }


    public Rental findById(Long id) {
        return  txManager.execute(session -> rentalRepository.findById(session, id));
    }

    public List<Rental> findAll() {
        return txManager.execute(rentalRepository::findAll);
    }

    public void delete(Rental rental) {
        txManager.execute(session -> {
            rentalRepository.delete(session, rental);
            return null;
        });
    }

    public void save(Rental rental) {
        txManager.execute(session -> {
            rentalRepository.save(session, rental);
            return null;
        });
    }

    public void update(Rental rental) {
        txManager.execute(session -> {
            rentalRepository.update(session, rental);
            return null;
        });
    }
}
