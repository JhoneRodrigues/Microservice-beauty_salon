package br.com.jhonerodrigues.adapters.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.jhonerodrigues.core.DTO.JobDTO;
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
	void findAllThenReturnAnListOfJobDTO() {
		when(service.findAll()).thenReturn(List.of(dto));
		
		ResponseEntity<List <JobDTO>> response = controller.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.getBody().size());
		assertEquals(JobDTO.class, response.getBody().get(0).getClass());
		assertEquals(dto.getName(), response.getBody().get(0).getName());
	}

	@Test
	void testFindById() {
		
	}

	private void startUser() {
		dto = new JobDTO(1L, "Corte", 30.00, 40);
	}
}
