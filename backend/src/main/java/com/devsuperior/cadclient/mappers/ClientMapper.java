package com.devsuperior.cadclient.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devsuperior.cadclient.entities.Client;
import com.devsuperior.cadclient.request.ClientRequest;
import com.devsuperior.cadclient.response.ClientResponse;

@Mapper
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	@Mapping(target = "id", ignore = true)
	Client clientRequestToClient(ClientRequest request);
	
	@Mapping(source = "id", target = "clientId")
	ClientResponse clientToClientResponse(Client entity);
}
