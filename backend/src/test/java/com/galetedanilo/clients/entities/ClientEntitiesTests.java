package com.galetedanilo.clients.entities;

import com.galetedanilo.clients.factories.ClientFactory;
import com.galetedanilo.clients.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@DataJpaTest
public class ClientEntitiesTests {

    @Autowired
    private ClientRepository clientRepository;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 100L;
    }

    @Test
    public void findClientByPrimaryKeyShouldReturnOptionalWithClientWhenIdExists() {
        Optional<Client> optionalClient = clientRepository.findById(existingId);

        Assertions.assertTrue(optionalClient.isPresent());
    }

    @Test
    public void findClientByPrimaryKeyShouldReturnEmptyOptionalWhenIdDoesNotExisting() {
        Optional<Client> optionalClient = clientRepository.findById(nonExistingId);

        Assertions.assertTrue(optionalClient.isEmpty());
    }

    @Test
    public void saveNewClientShouldPersistWithAutoincrementIdWhenIdIsNull() {
        Client client = ClientFactory.createClient();

        client = clientRepository.save(client);

        Assertions.assertNotNull(client.getId());
    }

    @Test
    public void updateClientShouldUpdateClientWhenIdExists() {
        Client client = clientRepository.getById(existingId);

        Client updateClient = ClientFactory.createClient();

        updateClient.setId(client.getId());

        client = clientRepository.save(updateClient);

        Assertions.assertEquals(client.getFirstName(), updateClient.getFirstName());
        Assertions.assertEquals(client.getLastName(), updateClient.getLastName());
        Assertions.assertEquals(client.getEmail(), updateClient.getEmail());
        Assertions.assertEquals(client.getCpf(), updateClient.getCpf());
        Assertions.assertEquals(client.getBirthDate(), updateClient.getBirthDate());
        Assertions.assertEquals(client.getAmount(), updateClient.getAmount());
        Assertions.assertEquals(client.getChildren(), updateClient.getChildren());
        Assertions.assertEquals(client.getCreatedAt(), updateClient.getCreatedAt());
    }

    @Test
    public void deleteClientByPrimaryKeyShouldDeleteObjectWhenIdExists() {
        clientRepository.deleteById(existingId);

        Optional<Client> clientOptional = clientRepository.findById(existingId);

        Assertions.assertTrue(clientOptional.isEmpty());
    }

    @Test
    public void deleteClientByPrimaryKeyShouldThrowsEmptyResultDataAccessExceptionWhenIdDoesNotExisting() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            clientRepository.deleteById(nonExistingId);
        });
    }
}
