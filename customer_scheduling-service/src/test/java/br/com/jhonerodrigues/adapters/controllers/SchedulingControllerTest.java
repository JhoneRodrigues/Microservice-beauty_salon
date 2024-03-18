package br.com.jhonerodrigues.adapters.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
import br.com.jhonerodrigues.core.usecases.SchedulingService;

@SpringBootTest
class SchedulingControllerTest {

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
	void FindAllThenReturnAnListOfSchedulingDTO() {
		when(service.findAll()).thenReturn(List.of(dto));
		
		ResponseEntity<List<SchedulingDTO>> response = controller.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.getBody().size());
		assertEquals(response.getBody().get(0).getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().get(0).getCol_day(), ConstsVar.SCHEDULING_DAY);
		assertEquals(response.getBody().get(0).getCol_time(), ConstsVar.SCHEDULING_TIME);
	}

	@Test
	void FindByIdThenReturnAnSchedulingDTO() {
		when(service.findById(anyLong())).thenReturn(dto);
		
		ResponseEntity<SchedulingDTO> response = controller.findById(ConstsVar.SCHEDULING_ID);
		
		assertNotNull(response);
		assertEquals(response.getBody().getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().getId(), ConstsVar.SCHEDULING_ID);
		assertEquals(response.getBody().getCol_day(), ConstsVar.SCHEDULING_DAY);
		assertEquals(response.getBody().getCol_time(), ConstsVar.SCHEDULING_TIME);
	}

	@Test
	void InsertSchedulingByUserIdThenReturnAnSchedulingDTO() {
		when(service.InsertSchedulingByUserId(anyLong(), any())).thenReturn(dto);
		
		ResponseEntity<SchedulingDTO> response = controller.insertSchedulingByUserId(ConstsVar.SCHEDULING_ID, request);
		
		assertNotNull(response);
		assertEquals(response.getBody().getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().getId(), ConstsVar.SCHEDULING_ID);
		assertEquals(response.getBody().getCol_day(), ConstsVar.SCHEDULING_DAY);
		assertEquals(response.getBody().getCol_time(), ConstsVar.SCHEDULING_TIME);
	}

	private void startUser() {
		dto = new SchedulingDTO(ConstsVar.SCHEDULING_ID, ConstsVar.SCHEDULING_DAY, ConstsVar.SCHEDULING_TIME,
				ConstsVar.SCHEDULING_PROF_ID, ConstsVar.CLIENT_ID, null);
		request = new SchedulingRequest(ConstsVar.SCHEDULING_DAY, ConstsVar.SCHEDULING_TIME, ConstsVar.SCHEDULING_PROF_ID, null);
	}
}
