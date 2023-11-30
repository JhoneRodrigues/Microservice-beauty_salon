package br.com.jhonerodrigues.adapters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.usecases.SchedulingService;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
	
	@Autowired
	private SchedulingService service;
	
	@GetMapping
	public ResponseEntity<List <SchedulingDTO> > findAll(){
		List <SchedulingDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SchedulingDTO> findById(@PathVariable Long id){
		var obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
