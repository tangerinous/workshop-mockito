package ru.yandex.workshop.mockito.customer;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> customers = Map.of(
        1L, new Customer(1L, "anna@example.com", "Anna", false, "REGULAR", 1),
        2L, new Customer(2L, "boris@example.com", "Boris", true, "NEW", 1),
        3L, new Customer(3L, "irina@example.com", "Irina", true, "VIP", 3),
        4L, new Customer(4L, "oleg@example.com", "Oleg", true, "UNKNOWN", 2)
    );

    @Override
    public Optional<Customer> findById(long customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }
}
