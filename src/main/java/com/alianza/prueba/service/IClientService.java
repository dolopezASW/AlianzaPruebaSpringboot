package com.alianza.prueba.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.alianza.prueba.dto.ClientDTO;

public interface IClientService {
	
	public ResponseEntity<ClientDTO> createClient(ClientDTO clientDto);
	
	public ResponseEntity<List<ClientDTO>> getClients();
	
	public ResponseEntity<List<ClientDTO>> filterClients(Map<String,String> filterParams);
	
	public ResponseEntity<List<ClientDTO>> searchClients(String key);


}
