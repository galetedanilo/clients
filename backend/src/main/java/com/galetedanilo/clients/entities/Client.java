package com.galetedanilo.clients.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_clients")
public class Client {

    @Id
    @SequenceGenerator(
            name = "client_id_sequence",
            sequenceName = "client_id_sequence"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private BigDecimal amount;
    private Integer children;
    private Instant birthDate;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

}
