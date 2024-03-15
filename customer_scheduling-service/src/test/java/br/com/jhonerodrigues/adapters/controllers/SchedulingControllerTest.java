package br.com.jhonerodrigues.adapters.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
	
	private static final long ID = 1L;
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
	void FindAllThenReturnAnListOfSchedulingDTO() {
		when(service.findAll()).thenReturn(List.of(dto));
		
		ResponseEntity<List<SchedulingDTO>> response = controller.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.getBody().size());
		assertEquals(response.getBody().get(0).getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().get(0).getCol_day(), DAY);
		assertEquals(response.getBody().get(0).getCol_time(), TIME);
	}

	@Test
	void FindByIdThenReturnAnSchedulingDTO() {
		when(service.findById(anyLong())).thenReturn(dto);
		
		ResponseEntity<SchedulingDTO> response = controller.findById(ID);
		
		assertNotNull(response);
		assertEquals(response.getBody().getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().getId(), ID);
		assertEquals(response.getBody().getCol_day(), DAY);
		assertEquals(response.getBody().getCol_time(), TIME);
	}

	@Test
	void InsertSchedulingByUserIdThenReturnAnSchedulingDTO() {
		when(service.InsertSchedulingByUserId(anyLong(), any())).thenReturn(dto);
		
		ResponseEntity<SchedulingDTO> response = controller.insertSchedulingByUserId(ID, request);
		
		assertNotNull(response);
		assertEquals(response.getBody().getClass(), SchedulingDTO.class);
		assertEquals(response.getBody().getId(), ID);
		assertEquals(response.getBody().getCol_day(), DAY);
		assertEquals(response.getBody().getCol_time(), TIME);
	}

	private void startUser() {
		dto = new SchedulingDTO(ID, DAY, TIME, PROF_ID, 1L, null);
		request = new SchedulingRequest(DAY, TIME, PROF_ID, null);
	}
}
