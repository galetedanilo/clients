package com.galetedanilo.clients.services;

import com.galetedanilo.clients.entities.Client;
import com.galetedanilo.clients.repositories.ClientRepository;
import com.galetedanilo.clients.requests.ClientRequest;
import com.galetedanilo.clients.responses.ClientResponse;
import com.galetedanilo.clients.services.exceptions.IntegrityViolationException;
import com.galetedanilo.clients.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientResponse findClientByPrimaryKey(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        Client entityClient = optionalClient.orElseThrow(() -> new ResourceNotFoundException("Client by id " + id + " does not exist"));

        return new ClientResponse(entityClient);
    }

    public Page<ClientResponse> findAllClients(Pageable pageable) {
        Page<Client> pageClients = clientRepository.findAll(pageable);

        return pageClients.map(obj -> new ClientResponse(obj));
    }

    public ClientResponse saveNewClient(ClientRequest clientRequest) {

        Client clientEntity = mapperClientRequestToClient(clientRequest);

        clientEntity.setCreatedAt(Instant.now());

        clientEntity = clientRepository.save(clientEntity);

        return  new ClientResponse(clientEntity);
    }

    public ClientResponse updateClient(Long id, ClientRequest clientRequest) {
        try {
            Client clientEntity = mapperClientRequestToClient(clientRequest);

            clientEntity.setId(id);
            clientEntity.setUpdatedAt(Instant.now());

            clientEntity = clientRepository.save(clientEntity);

            return new ClientResponse(clientEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    public void deleteClientByPrimaryKey(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegrityViolationException("Data integrity violation");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Client id " + id + " not found");
        }
    }

    private Client mapperClientRequestToClient(ClientRequest clientRequest) {
        Client client = new Client();

        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setEmail(clientRequest.getEmail());
        client.setCpf(clientRequest.getCpf());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setChildren(clientRequest.getChildren());
        client.setAmount(clientRequest.getAmount());

        return client;
    }

}
