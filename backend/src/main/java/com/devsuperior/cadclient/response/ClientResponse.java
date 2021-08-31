package com.devsuperior.cadclient.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

	private Long clientId;
	
	private String name;
	
	private String cpf;
	
	private BigDecimal income;
	
	private String birthDate;
	
	private Integer children;
}
