package ru.yandex.workshop.mockito.customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(long customerId);
}
