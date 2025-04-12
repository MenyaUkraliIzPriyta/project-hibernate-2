package org.example;

import org.example.enums.Rating;
import org.example.repository.*;
import org.example.service.CustomerService;
import org.example.service.FilmService;
import org.example.service.InventoryService;
import org.example.service.RentalService;
import org.example.utils.SessionCreator;
import org.example.utils.TransactionManager;
import org.hibernate.Session;
import sun.security.mscapi.CPublicKey;

import java.math.BigDecimal;
import java.time.Year;

public class App {
    public static SessionCreator sessionCreator = new SessionCreator();
    public static TransactionManager transactionManager = new TransactionManager(sessionCreator);
    public static void main( String[] args ) {


        RentalService rentalService = new RentalService(new RentalRepository(), transactionManager);
        System.out.println( rentalService.returnFilm());

        FilmService filmService = new FilmService(new FilmRepository(), transactionManager, new LanguageRepository());
        System.out.println(filmService.newFilmWasReleased("Boys", Year.now(),50, BigDecimal.ZERO, 160, BigDecimal.TEN, Rating.PG));

        CustomerService customerService = new CustomerService(new CustomerRepository(), new AddressRepository(), transactionManager, new StoreRepository());
        System.out.println(customerService.saveWithDependencies("Nick", "K", "example28@gmail.com"));

        InventoryService inventoryService = new InventoryService(new InventoryRepository(), transactionManager, new CustomerRepository(), new StoreRepository(), new RentalRepository(), new PaymentRepository());
    }
}
