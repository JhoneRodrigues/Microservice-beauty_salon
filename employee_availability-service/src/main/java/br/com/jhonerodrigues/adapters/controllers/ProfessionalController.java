package br.com.jhonerodrigues.adapters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.usecases.ProfessionalService;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
	
	@Autowired
	private ProfessionalService service;
	
	@GetMapping
	public ResponseEntity<List<ProfessionalDTO>> findAll (){
		return ResponseEntity.ok().body(service.findAll());
	}
}
