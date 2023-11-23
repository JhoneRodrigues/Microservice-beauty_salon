package br.com.jhonerodrigues.adapters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.usecases.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	private JobService service;
	
	@GetMapping
	public ResponseEntity<List <JobDTO> > findAll(){
		List <JobDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
}
