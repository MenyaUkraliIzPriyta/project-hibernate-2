package org.example.repository;

import org.example.entity.Payment;
import sun.reflect.generics.repository.AbstractRepository;

public class PaymentRepository extends FoundationReposirory<Payment> {
    public PaymentRepository() {
        super(Payment.class);
    }
}
