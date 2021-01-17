package com.alianza.prueba.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.prueba.dto.ClientDTO;
import com.alianza.prueba.service.IClientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private IClientService clientService;
	
	@PostMapping("/createClient")
	public ResponseEntity createClient(@RequestBody ClientDTO clientDto) {
		return clientService.createClient(clientDto);
		
	}
	
	@GetMapping("/getClients")
	public ResponseEntity getClients() {
		return clientService.getClients();
	}
	
	@PostMapping("/filterClients")
	public ResponseEntity filterClients(Map<String,String> filterParams) {
		return clientService.filterClients(filterParams);
	}
	
	@GetMapping("/searchClients")
	public ResponseEntity searchClients() {
		return clientService.getClients();
	}

}
