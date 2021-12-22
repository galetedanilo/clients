package com.galetedanilo.clients.requests;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "The first name is required")
    private String firstName;
    @NotBlank(message = "The last name is required")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @CPF(message = "The CPF is required")
    private String cpf;
    @NotBlank(message = "The amount is required")
    private BigDecimal amount;
    @PositiveOrZero(message = "Positive number")
    private Integer children;
    private Instant birthDate;
}
