package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.*;
import org.example.repository.*;
import org.example.utils.TransactionManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final TransactionManager txManager;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;

    public Inventory customerRentInventory() {
        return txManager.execute(session -> {
            Customer customer = customerRepository.findById(session, 1L);
            Store store = storeRepository.findById(session, 1L);
            Staff staff = store.getStaff();

            Film film = inventoryRepository.createQueryList(
                    session,
                    Film.class,
                    "select f from Film f where f.id not in (select film.id from Inventory)"
            ).get(0);

            Inventory inventory = Inventory.builder()
                    .film(film)
                    .store(store)
                    .build();
            inventoryRepository.save(session, inventory);

            Rental rental = Rental.builder()
                    .rentalDate(LocalDateTime.now())
                    .customer(customer)
                    .staff(staff)
                    .inventory(inventory)
                    .build();
            rentalRepository.save(session, rental);

            Payment payment = Payment.builder()
                    .rental(rental)
                    .paymentDate(LocalDateTime.now())
                    .customer(customer)
                    .amount(BigDecimal.valueOf(52.00))
                    .staff(staff)
                    .build();
            paymentRepository.save(session, payment);

            return inventory;
        });
    }

    public Inventory findById(Long id) {
        return txManager.execute(session -> inventoryRepository.findById(session, id));
    }

    public List<Inventory> findAll() {
        return txManager.execute(inventoryRepository::findAll);
    }

    public void delete(Inventory inventory) {
        txManager.execute(session -> {
            inventoryRepository.delete(session, inventory);
            return null;
        });
    }

    public void save(Inventory store) {
        txManager.execute(session -> {
            inventoryRepository.save(session, store);
            return null;
        });
    }

    public void update(Inventory store) {
        txManager.execute(session -> {
            inventoryRepository.update(session, store);
            return null;
        });
    }
}
