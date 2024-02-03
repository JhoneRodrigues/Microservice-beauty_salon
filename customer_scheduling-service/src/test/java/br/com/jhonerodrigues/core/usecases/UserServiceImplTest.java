package br.com.jhonerodrigues.core.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.DataIntegratyViolationException;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.requests.UserRequest;
import br.com.jhonerodrigues.infra.user.UserRepositoryImpl;

@SpringBootTest
class UserServiceImplTest {
	
	private static final String PHONE = "11 90001-0002";
	private static final LocalDate BIRTHDAY = LocalDate.parse("2000-01-02");
	private static final String NAME = "Duratestelson de Melo";
	private static final long ID = 1L;

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepositoryImpl repository;
	
	private User user;
	private UserDTO userDTO;
	private UserRequest userRequest;
	private Optional<User> optionalUser;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		when(repository.findById(anyLong())).thenReturn(user);
		
		UserDTO response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(UserDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(PHONE, response.getPhone());
	}
	
	@Test
	void whenFindByIdThenReturnAnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(ID));
		
		try {
			service.findById(ID);
		} catch(Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
	}
	
	@Test
	void FindAllThenReturnAnListOfUsersDTO() {
		when(repository.findAll()).thenReturn(List.of(user));
		
		List<UserDTO> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(UserDTO.class, response.get(0).getClass());
		assertEquals(PHONE, response.get(0).getPhone());
	}

	@Test
	void InsertThenReturnSuccess() {
		when(repository.insert(any())).thenReturn(user);
		
		UserDTO response = service.insert(userRequest);
		
		assertNotNull(response);
		assertEquals(UserDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(PHONE, response.getPhone());
	}
	
	@Test
	void InsertThenReturnAnDataIntegrityViolationException() {
		when(repository.findByPhone(anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2L);
			service.insert(userRequest);
		} catch(Exception ex) {
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
		}
	}

	@Test
	void testUpdate() {
		
	}
	
	private void startUser() {
		user = new User(ID , NAME, BIRTHDAY, PHONE);
		userDTO = new UserDTO(ID , NAME, BIRTHDAY, PHONE);
		userRequest = new UserRequest(NAME, BIRTHDAY, PHONE);
		optionalUser = Optional.of(new User(ID , NAME, BIRTHDAY, PHONE));
	}
}









