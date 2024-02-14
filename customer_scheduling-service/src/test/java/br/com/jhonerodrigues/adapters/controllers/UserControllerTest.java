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

import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.requests.UserRequest;
import br.com.jhonerodrigues.core.usecases.UserService;

@SpringBootTest
class UserControllerTest {
	
	private static final String PHONE = "(00) 90001-0002";

	private static final String NAME = "Gerson";

	private static final LocalDate BIRTHDAY = LocalDate.parse("2000-02-02");

	private static final long ID = 1L;

	@InjectMocks
	private UserController controller;
	
	@Mock
	private UserService service;
	
	private UserDTO dto;
	private UserRequest request;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void FindAllThenReturnListOfUserDTO() {
		when(service.findAll()).thenReturn(List.of(dto));
		
		ResponseEntity <List<UserDTO>> response = controller.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.getBody().size());
		assertEquals(UserDTO.class, response.getBody().get(0).getClass());
		assertEquals(NAME, response.getBody().get(0).getName());
	}

	@Test
	void FindByIdThenReturnAnUserDTO() {
		when(service.findById(anyLong())).thenReturn(dto);
		
		ResponseEntity<UserDTO> response = controller.findById(ID);
		
		assertNotNull(response);
		assertEquals(response.getBody().getClass(), UserDTO.class);
		assertEquals(response.getBody().getId(), ID);
		assertEquals(response.getBody().getPhone(), PHONE);
	}

	@Test
	void InsetThenReturnAnUserDTO() {
		when(service.insert(any())).thenReturn(dto);
		
		ResponseEntity<UserDTO> response = controller.insert(request);
		
		assertNotNull(response);
		assertEquals(response.getBody().getId(), ID);
		assertEquals(response.getBody().getPhone(), PHONE);
	}
	
	private void startUser() {
		dto = new UserDTO(ID, NAME, BIRTHDAY, PHONE);
		request = new UserRequest(NAME, BIRTHDAY, PHONE);
	}
}
