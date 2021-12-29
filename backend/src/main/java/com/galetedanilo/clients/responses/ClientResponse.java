package com.galetedanilo.clients.responses;

import com.galetedanilo.clients.controllers.ClientController;
import com.galetedanilo.clients.entities.Client;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse extends RepresentationModel<ClientResponse> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private BigDecimal amount;
    private Integer children;
    private Instant birthDate;
    private Instant createdAt;
    private Instant updatedAt;

    public ClientResponse(Client entityClient) {
        this.id = entityClient.getId();
        this.firstName = entityClient.getFirstName();
        this.lastName = entityClient.getLastName();
        this.email = entityClient.getEmail();
        this.cpf = entityClient.getCpf();
        this.amount = entityClient.getAmount();
        this.children = entityClient.getChildren();
        this.birthDate = entityClient.getBirthDate();
        this.createdAt = entityClient.getCreatedAt();
        this.updatedAt = entityClient.getUpdatedAt();

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).findClientByPrimaryKey(id))
                .withSelfRel()
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).deleteClientByPrimaryKey(id))
                .withRel("Delete client")
        );

        this.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).findAllClients(PageRequest.of(0, 20)))
                .withRel("Find all clients")
        );
    }
}
