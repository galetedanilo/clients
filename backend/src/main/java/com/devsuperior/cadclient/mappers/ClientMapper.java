package com.devsuperior.cadclient.mappers;

import org.mapstruct.Mapper;

import com.devsuperior.cadclient.entities.Client;
import com.devsuperior.cadclient.request.ClientRequest;
import com.devsuperior.cadclient.response.ClientResponse;

@Mapper
public interface ClientMapper {

	Client clientRequestToClient(ClientRequest request);
	
	ClientResponse clientToClientResponse(Client entity);
}
