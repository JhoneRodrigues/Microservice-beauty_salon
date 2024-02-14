package br.com.jhonerodrigues.adapters.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
import br.com.jhonerodrigues.core.usecases.SchedulingService;

@SpringBootTest
class SchedulingControllerTest {
	
	private static final long PROF_ID = 1L;
	private static final StandardTimes TIME = StandardTimes.T09_11;
	private static final LocalDate DAY = LocalDate.parse("2023-02-02");

	@InjectMocks
	private SchedulingController controller;
	
	@Mock
	private SchedulingService service;
	
	private SchedulingDTO dto;
	private SchedulingRequest request;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void FindAllThenReturnAnListOfSchedulingsDTO() {
		when(service.findAll()).thenReturn(List.of(dto));
		
		ResponseEntity<List<SchedulingDTO>> response = controller.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.getBody().size());
		assertEquals(response.getBody().get(0).getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().get(0).getCol_day(), DAY);
		assertEquals(response.getBody().get(0).getCol_time(), TIME);
	}

	@Test
	void testFindById() {
	}

	@Test
	void testInsertSchedulingByUserId() {
	}

	private void startUser() {
		dto = new SchedulingDTO(1L, DAY, TIME, PROF_ID, null);
		request = new SchedulingRequest(DAY, TIME, PROF_ID, null);
	}
}
