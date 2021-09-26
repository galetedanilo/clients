package com.devsuperior.cadclient.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.cadclient.entities.Client;
import com.devsuperior.cadclient.mappers.ClientMapper;
import com.devsuperior.cadclient.repositories.ClientRepository;
import com.devsuperior.cadclient.request.ClientRequest;
import com.devsuperior.cadclient.response.ClientResponse;
import com.devsuperior.cadclient.services.exceptions.DatabaseException;
import com.devsuperior.cadclient.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final ClientRepository clientRepository;
	
	private final ClientMapper clientMapper = ClientMapper.INSTANCE;
	
	
	@Transactional(readOnly = true)
	public Page<ClientResponse> findAllClientsPaged(Pageable pageable) {
		Page<Client> page = clientRepository.findAll(pageable);
		
		return page.map(client -> clientMapper.clientToClientResponse(client));
	}
	
	@Transactional(readOnly = true)
	public ClientResponse findClientById(Long id) {
		Optional<Client> obj = clientRepository.findById(id);
		
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return clientMapper.clientToClientResponse(entity);		
	}
	
	@Transactional
	public ClientResponse insertClient(ClientRequest request) {
		Client entity = clientMapper.clientRequestToClient(request);
		
		entity = clientRepository.save(entity);
		
		return clientMapper.clientToClientResponse(entity);
	}
	
	@Transactional
	public ClientResponse updateClient(Long id, ClientRequest request) {
		verifyIfClientExists(id);
		
		Client entity = clientMapper.clientRequestToClient(request);
		
		entity.setId(id);
		
		entity = clientRepository.save(entity);
		
		return clientMapper.clientToClientResponse(entity);
	}
	
	public void deleteClient(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Integrity violation");
		}	
	}

	private Client verifyIfClientExists(Long id) {
		return clientRepository.findById(id).
					orElseThrow(() -> new ResourceNotFoundException(String.format("Client by id %s was not found", id)));
	}
}
