package br.com.jhonerodrigues.adapters.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.jhonerodrigues.ConstsVar;
import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.usecases.JobService;

@SpringBootTest
class JobControllerTest {

	@InjectMocks
	private JobController controller;
	
	@Mock
	private JobService service;
	
	private JobDTO dto;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void FindAllThenReturnAnListOfJobDTO() {
		when(service.findAll()).thenReturn(List.of(dto));
		
		ResponseEntity<List <JobDTO>> response = controller.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.getBody().size());
		assertEquals(JobDTO.class, response.getBody().get(0).getClass());
		assertEquals(dto.getName(), response.getBody().get(0).getName());
	}

	@Test
	void FindByIdThenReturnAnJobDTO() {
		when(service.findById(anyLong())).thenReturn(dto);
		
		ResponseEntity<JobDTO> response = controller.findById(ConstsVar.JOB_ID);
		
		assertNotNull(response);
		assertEquals(JobDTO.class, response.getBody().getClass());
		assertEquals(dto.getId(), ConstsVar.JOB_ID);
		assertEquals(dto.getName(), response.getBody().getName());
	}
	
	@Test
	void FindByIdThenReturnAnResourceNotFoundException() {
		when(service.findById(anyLong())).thenThrow(new ResourceNotFoundException(ConstsVar.JOB_ID));
		
		try {
			controller.findById(ConstsVar.JOB_ID);
		} catch (Exception e){
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	private void startUser() {
		dto = new JobDTO(ConstsVar.JOB_ID, ConstsVar.JOB_NAME, ConstsVar.JOB_PRICE, ConstsVar.JOB_DURATION);
	}
}
