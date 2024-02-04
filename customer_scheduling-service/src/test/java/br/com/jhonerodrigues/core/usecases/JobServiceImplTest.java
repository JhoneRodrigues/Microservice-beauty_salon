package br.com.jhonerodrigues.core.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jhonerodrigues.adapters.gateways.JobRepository;
import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

class JobServiceImplTest {

	private static final int DURATION = 40;
	private static final double PRICE = 30.00;
	private static final String NAME = "Corte";
	private static final long ID = 1L;

	@InjectMocks
	private JobServiceImpl service;

	private JobDTO jobDTO;
	private Job job;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Mock
	private JobRepository repository;

	@Test
	void testFindAll() {

	}

	@Test
	void FindByIdThenReturnJobInstance() {
		when(repository.findById(anyLong())).thenReturn(job);
		
		JobDTO response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(JobDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
	}

	@Test
	void FindByIdThenReturnAnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(ID));
		
		try {
			service.findById(ID);
		} catch(Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
	}

	private void startUser() {
		job = new Job(ID, NAME, PRICE, DURATION);
		jobDTO = new JobDTO(job);
	}
}
