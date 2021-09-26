package com.devsuperior.cadclient.controllers;

import java.io.Serializable;
import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.cadclient.request.ClientRequest;
import com.devsuperior.cadclient.response.ClientResponse;
import com.devsuperior.cadclient.services.ClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientController implements Serializable {
	private static final long serialVersionUID = 1L;

	private final ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Page<ClientResponse>> findAllClientsPaged(Pageable pageable) {
		Page<ClientResponse> page = clientService.findAllClientsPaged(pageable);
		
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientResponse> findClientById(@PathVariable Long id) {
		ClientResponse response = clientService.findClientById(id);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClientResponse> insertClient(@RequestBody ClientRequest request) {
		ClientResponse response = clientService.insertClient(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(response.getClientId()).toUri();
		
		return ResponseEntity.created(uri).body(response);		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientResponse> updateVlient(@PathVariable Long id, @RequestBody ClientRequest request) {
		ClientResponse response = clientService.updateClient(id, request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		clientService.deleteClient(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
