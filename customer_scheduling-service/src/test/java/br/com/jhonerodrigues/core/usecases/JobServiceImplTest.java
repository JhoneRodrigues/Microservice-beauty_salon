package br.com.jhonerodrigues.core.usecases;

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

import br.com.jhonerodrigues.ConstsVar;
import br.com.jhonerodrigues.adapters.gateways.JobRepository;
import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

@SpringBootTest
class JobServiceImplTest {

	@InjectMocks
	private JobServiceImpl service;
	
	@Mock
	private JobRepository repository;

	private JobDTO jobDTO;
	private Job job;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void FindAllThenReturnAnListOfJobsDTO() {
		when(repository.findAll()).thenReturn(List.of(job));
		
		List<JobDTO> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(JobDTO.class, response.get(0).getClass());
		assertEquals(ConstsVar.JOB_NAME, response.get(0).getName());
	}

	@Test
	void FindByIdThenReturnJobInstance() {
		when(repository.findById(anyLong())).thenReturn(job);
		
		JobDTO response = service.findById(ConstsVar.JOB_ID);
		
		assertNotNull(response);
		assertEquals(JobDTO.class, response.getClass());
		assertEquals(ConstsVar.JOB_ID, response.getId());
		assertEquals(ConstsVar.JOB_NAME, response.getName());
	}

	@Test
	void FindByIdThenReturnAnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(ConstsVar.JOB_ID));
		
		try {
			service.findById(ConstsVar.JOB_ID);
		} catch(Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
	}

	private void startUser() {
		job = new Job(ConstsVar.JOB_ID, ConstsVar.JOB_NAME, ConstsVar.JOB_PRICE, ConstsVar.JOB_DURATION);
		jobDTO = new JobDTO(job);
	}
}
