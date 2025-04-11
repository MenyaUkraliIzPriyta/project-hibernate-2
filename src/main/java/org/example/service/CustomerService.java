package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Store;
import org.example.repository.AddressRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.StoreRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final TransactionManager txManager;
    private final StoreRepository storeRepository;

    public Customer saveWithDependencies(String firstName, String lastName, String email) {
        return txManager.execute(session -> {
            Store store = storeRepository.findById(session, 1L);
            Address address = addressRepository.findById(session, 1L);

            Customer customer = Customer.builder()
                    .store(store)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .address(address)
                    .isActive(true)
                    .build();

            customerRepository.save(session, customer);
            return customer;
        });
    }


    public Customer findById(Long id) {
        return txManager.execute(session -> customerRepository.findById(session, id));
    }

    public List<Customer> findAll() {
        return txManager.execute(customerRepository::findAll);
    }

    public void delete(Customer customer) {
        txManager.execute(session -> {
            customerRepository.delete(session, customer);
            return null;
        });
    }

    public void save(Customer customer) {
        txManager.execute(session -> {
            customerRepository.save(session, customer);
            return null;
        });
    }

    public void update(Customer customer) {
        txManager.execute(session -> {
            customerRepository.update(session, customer);
            return null;
        });
    }
}
