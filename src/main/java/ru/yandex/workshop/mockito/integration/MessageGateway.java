package ru.yandex.workshop.mockito.integration;

public interface MessageGateway {

    void sendRewardIssuedMessage(String email, String message);
}
