package com.devsuperior.cadclient.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
	
	@NotBlank(message = "The name is required")
	private String name;
	
	@NotBlank(message = "The CPF is required")
	@CPF(message = "CPF invalid")
	private String cpf;
	
	@NotBlank(message = "The income is required")
	private BigDecimal income;
	
	@NotBlank(message = "The birth date is required")
	private String birthDate;
	
	@NotBlank(message = "The children is required")
	private Integer children;
}
