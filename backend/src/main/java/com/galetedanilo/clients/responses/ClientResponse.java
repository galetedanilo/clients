package com.galetedanilo.clients.responses;

import com.galetedanilo.clients.entities.Client;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse implements Serializable {

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
    }
}
