package com.alianza.prueba.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDTO {
	
	private String name;
	private String phone;
	private String email;
	private LocalDate startDate;
	private LocalDate endDate;

}
