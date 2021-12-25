package com.galetedanilo.clients.controllers;

import com.galetedanilo.clients.requests.ClientRequest;
import com.galetedanilo.clients.responses.ClientResponse;
import com.galetedanilo.clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> findAllClients(Pageable pageable) {
        Page<ClientResponse> clientPage = clientService.findAllClients(pageable);

        clientPage.forEach(
                clientResponse -> {
                    clientResponse.add(WebMvcLinkBuilder
                            .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).findClientByPrimaryKey(clientResponse.getClientId()))
                            .withSelfRel()
                    );

                    clientResponse.add(WebMvcLinkBuilder
                            .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).deleteClientByPrimaryKey(clientResponse.getClientId()))
                            .withRel("Delete clients")
                    );
                }
        );

        return new ResponseEntity<>(clientPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> findClientByPrimaryKey(@PathVariable Long id) {
        ClientResponse clientResponse = clientService.findClientByPrimaryKey(id);

        clientResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).findAllClients(PageRequest.of(0, 20)))
                .withRel("Find all clients")
        );

        clientResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).deleteClientByPrimaryKey(id))
                .withRel("Delete client")
        );

        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> saveNewClient(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.saveNewClient(clientRequest);

        clientResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).findAllClients(PageRequest.of(0, 20)))
                .withRel("Find all clients")
        );

        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<ClientResponse> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.updateClient(id, clientRequest);

        clientResponse.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).findAllClients(PageRequest.of(0, 20)))
                .withRel("Find all clients")
        );

        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClientByPrimaryKey(@PathVariable Long id) {
        clientService.deleteClientByPrimaryKey(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
