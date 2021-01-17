package com.alianza.prueba.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alianza.prueba.dto.ClientDTO;
import com.alianza.prueba.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {
	
	private static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	/**
	 * Crea un nuevo cliente
	 * Si no es posible crearlo debido a un error, retorna null en su lugar
	 */
	@Override
	public ResponseEntity<ClientDTO> createClient(ClientDTO clientDto) {
		try {
			if(!this.validateClient(clientDto)) {
				throw new Exception();
			}
			logger.debug("Executing method ");
			ClientDTO clientRespDto = new ClientDTO("", "", "", LocalDate.now(), LocalDate.now().plusMonths(24));;
			return new ResponseEntity<>(clientRespDto,HttpStatus.OK);
		}catch(Exception ex) {
			logger.error("Error. Failed executing method createClient");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retorna la lista completa de clientes
	 * Si el servicio falla retorna una lista nula
	 */
	@Override
	public ResponseEntity<List<ClientDTO>> getClients() {
		try {
			logger.debug("Executing method getClients");
			List<ClientDTO> lstClients = new ArrayList<>();
			lstClients = this.fillClientList();
			return new ResponseEntity<>(lstClients,HttpStatus.OK);
		}catch(Exception ex) {
			logger.error("Error. Failed executing method ");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * Retorna la lista de clientes segun parametros de busqueda
	 * Si el servicio falla retorna una lista nula
	 */
	@Override
	public ResponseEntity<List<ClientDTO>> filterClients(Map<String, String> filterParams) {
		try {
			logger.debug("Executing method ");
			List<ClientDTO> lstClients = new ArrayList<>();
			lstClients = this.fillClientList();
			return new ResponseEntity<>(lstClients,HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception ex) {
			logger.error("Error. Failed executing method filterClients");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Llena la lista con datos dummy
	 * @return
	 */
	private List<ClientDTO> fillClientList() {
		List<ClientDTO> clientList = new ArrayList<>();
		
		clientList.add(new ClientDTO("Jorge Paez","313 424 12 75","jpaez@gmail.com",LocalDate.now(), LocalDate.now().plusMonths(24)));
		clientList.add(new ClientDTO("Sergio Valdez","311 8907236","svaldez@hotmail.com",LocalDate.now(), LocalDate.now().plusMonths(24)));
		clientList.add(new ClientDTO("Maria Perlaza","+57 311 858 03 21","maria.perlaza83@gmail.com",LocalDate.now(), LocalDate.now().plusMonths(24)));
		clientList.add(new ClientDTO("Sonia Munoz","+57 320 444 23 67","sonia.cosmica@outlook.es",LocalDate.now(), LocalDate.now().plusMonths(24)));
		return clientList;
	}
	
	/**
	 * Valida la correctitud de los datos y los datos obligatorios
	 * Retorna true si son correctos, false en caso contrario.
	 * @param client
	 * @return
	 */
	private boolean validateClient(ClientDTO client) {
		boolean isValid = false;
		
		Pattern pattern = Pattern.compile("^(+\\d{3}[- .]?){2}\\d{4}$");
	   
		isValid = isValid && client.getStartDate()!=null;
		isValid = isValid && client.getEndDate()!=null && client.getEndDate().isAfter(client.getEndDate());
		isValid = isValid && client.getName().trim()!=null;
		Matcher matcher = pattern.matcher(client.getPhone());
		isValid = isValid && client.getPhone().trim()!=null && matcher.matches();
		return isValid;
		
	}

	/**
	 * Filtra los clientes por shared key
	 * Retorna la lista con los clientes que cumplen la condicion
	 */
	@Override
	public ResponseEntity<List<ClientDTO>> searchClients(String key) {
		try {
			
			logger.debug("Executing method ");
			List<ClientDTO> lstClientsDTO = this.fillClientList();
			for(ClientDTO clientDTO: lstClientsDTO) {
				if(!clientDTO.getEmail().split("@")[0].contains(key)) {
					lstClientsDTO.remove(clientDTO);
				}
			}
			return new ResponseEntity<>(lstClientsDTO,HttpStatus.OK);
		}catch(Exception ex) {
			logger.error("Error. Failed executing method createClient");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
