package org.example.repository;

import org.example.entity.Address;
import org.example.entity.Customer;

public class AddressRepository extends FoundationReposirory<Address> {
    public AddressRepository() {
        super(Address.class);
    }
}
