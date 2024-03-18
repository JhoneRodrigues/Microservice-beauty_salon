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
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.requests.UserRequest;
import br.com.jhonerodrigues.core.usecases.UserService;

@SpringBootTest
class UserControllerTest {

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
		assertEquals(ConstsVar.CLIENT_NAME, response.getBody().get(0).getName());
	}

	@Test
	void FindByIdThenReturnAnUserDTO() {
		when(service.findById(anyLong())).thenReturn(dto);
		
		ResponseEntity<UserDTO> response = controller.findById(ConstsVar.CLIENT_ID);
		
		assertNotNull(response);
		assertEquals(response.getBody().getClass(), UserDTO.class);
		assertEquals(response.getBody().getId(), ConstsVar.CLIENT_ID);
		assertEquals(response.getBody().getPhone(), ConstsVar.CLIENT_PHONE);
	}

	@Test
	void InsetThenReturnAnUserDTO() {
		when(service.insert(any())).thenReturn(dto);
		
		ResponseEntity<UserDTO> response = controller.insert(request);
		
		assertNotNull(response);
		assertEquals(response.getBody().getId(), ConstsVar.CLIENT_ID);
		assertEquals(response.getBody().getPhone(), ConstsVar.CLIENT_PHONE);
	}
	
	private void startUser() {
		dto = new UserDTO(ConstsVar.CLIENT_ID, ConstsVar.CLIENT_NAME, ConstsVar.CLIENT_BIRTHDAY, ConstsVar.CLIENT_PHONE);
		request = new UserRequest(ConstsVar.CLIENT_NAME, ConstsVar.CLIENT_BIRTHDAY, ConstsVar.CLIENT_PHONE);
	}
}
