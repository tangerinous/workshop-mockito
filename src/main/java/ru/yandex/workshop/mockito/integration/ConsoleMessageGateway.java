package ru.yandex.workshop.mockito.integration;

import org.springframework.stereotype.Component;

@Component
public class ConsoleMessageGateway implements MessageGateway {

    @Override
    public void sendRewardIssuedMessage(String email, String message) {
        System.out.println("Sending message to " + email + ": " + message);
    }
}
