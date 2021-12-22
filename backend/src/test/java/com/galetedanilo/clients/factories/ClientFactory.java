package com.galetedanilo.clients.factories;

import com.galetedanilo.clients.entities.Client;

import java.math.BigDecimal;
import java.time.Instant;

public class ClientFactory {

    public static Client createClient() {

        Client client = new Client();

        client.setId(null);
        client.setFirstName("Pedro");
        client.setLastName("Cunha");
        client.setEmail("pedrocunha@gmail.com");
        client.setCpf("364.789.154-75");
        client.setAmount(BigDecimal.valueOf(2754.75));
        client.setChildren(2);
        client.setBirthDate(Instant.now());
        client.setCreatedAt(Instant.now());

        return client;
    }
}
