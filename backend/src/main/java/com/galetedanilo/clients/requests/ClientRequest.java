package com.galetedanilo.clients.requests;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "The first name is required")
    private String firstName;
    @NotBlank(message = "The last name is required")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @CPF(message = "The CPF is required")
    private String cpf;
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount of being a value greater than zero")
    @DecimalMax(value = "100000.00", inclusive = false, message = "Amount cannot be greater than one hundred thousand")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;
    @PositiveOrZero(message = "Positive number")
    private Integer children;
    private Instant birthDate;
}
