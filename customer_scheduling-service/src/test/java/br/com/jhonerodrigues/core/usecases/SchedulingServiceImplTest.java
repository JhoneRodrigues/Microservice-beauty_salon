package br.com.jhonerodrigues.core.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

class SchedulingServiceImplTest {
	
	private static final StandardTimes TIME = StandardTimes.T09_11;
	private static final LocalDate DATE = LocalDate.parse("2000-02-02");
	private static final long ID = 1L;

	@InjectMocks
	private SchedulingServiceImpl schedulingService;
	
	@Mock
	private SchedulingRepository repository;
	
	private Scheduling scheduling;
	private SchedulingDTO schedulingDTO;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void testFindAll() {
	}

	@Test
	void FindByIdThenReturnAnUserInstance() {
		when(repository.findById(anyLong())).thenReturn(scheduling);
		
		SchedulingDTO response = schedulingService.findById(ID);
		
		assertNotNull(response);
		assertEquals(SchedulingDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(DATE, response.getCol_day());
	}
	
	@Test
	void whenFindByIdThenReturnAnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(ID));
		
		try {
			schedulingService.findById(ID);
		} catch(Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
	}
	
	private void startUser() {
		scheduling = new Scheduling(ID, DATE, TIME);
		schedulingDTO = new SchedulingDTO(scheduling);
 	}
}
