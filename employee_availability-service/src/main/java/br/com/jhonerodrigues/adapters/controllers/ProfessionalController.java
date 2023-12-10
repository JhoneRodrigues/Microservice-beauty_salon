package br.com.jhonerodrigues.adapters.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.requests.ProfessionalRequest;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfessionalDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<ProfessionalDTO> insert(@RequestBody ProfessionalRequest request){
		var obj = service.insert(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProfessionalDTO> update (@PathVariable Long id, 
			@RequestBody ProfessionalRequest request){
		var obj = service.update(id, request);
		return ResponseEntity.ok().body(obj);
	}
}
