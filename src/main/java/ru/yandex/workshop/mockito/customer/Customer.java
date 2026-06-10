package ru.yandex.workshop.mockito.customer;

public record Customer(
    long id,
    String email,
    String name,
    boolean active,
    String segment,
    int loyaltyLevel
) {
}
