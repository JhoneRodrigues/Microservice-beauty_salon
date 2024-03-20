package br.com.jhonerodrigues.core.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jhonerodrigues.ConstsVar;
import br.com.jhonerodrigues.adapters.gateways.UserRepository;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.DataIntegratyViolationException;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.requests.UserRequest;

@SpringBootTest
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

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
		
		UserDTO response = service.findById(ConstsVar.CLIENT_ID);
		
		assertNotNull(response);
		assertEquals(UserDTO.class, response.getClass());
		assertEquals(ConstsVar.CLIENT_ID, response.getId());
		assertEquals(ConstsVar.CLIENT_PHONE, response.getPhone());
	}

	@Test
	void whenFindByIdThenReturnAnResourceNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(ConstsVar.CLIENT_ID));
		
		try {
			service.findById(ConstsVar.CLIENT_ID);
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
		assertEquals(ConstsVar.CLIENT_PHONE, response.get(0).getPhone());
	}

	@Test
	void InsertThenReturnSuccess() {
		when(repository.insert(any())).thenReturn(user);
		
		UserDTO response = service.insert(userRequest);
		
		assertNotNull(response);
		assertEquals(UserDTO.class, response.getClass());
		assertEquals(ConstsVar.CLIENT_ID, response.getId());
		assertEquals(ConstsVar.CLIENT_PHONE, response.getPhone());
	}

	@Test
	void InsertThenReturnAnDataIntegrityViolationException() {
		when(repository.findByPhone(anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(anyLong());
			service.insert(userRequest);
		} catch(Exception ex) {
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
		}
	}

	@Test
	void UpdateThenReturnSuccess() {
		when(repository.update(anyLong(),any())).thenReturn(user);
		
		UserDTO response = service.update(ConstsVar.CLIENT_ID,user);
		
		assertNotNull(response);
		assertEquals(UserDTO.class, response.getClass());
		assertEquals(ConstsVar.CLIENT_ID, response.getId());
		assertEquals(ConstsVar.CLIENT_PHONE, response.getPhone());
	}

	@Test
	void UpdateThenReturnAnDataIntegrityViolationException() {
		when(repository.findByPhone(anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(anyLong());
			service.update(ConstsVar.CLIENT_ID,user);
		} catch(Exception ex) {
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
		}
	}

	private void startUser() {
		user = new User(ConstsVar.CLIENT_ID, ConstsVar.CLIENT_NAME, ConstsVar.CLIENT_BIRTHDAY,
				ConstsVar.CLIENT_PHONE, ConstsVar.CLIENT_EMAIL);
		userDTO = new UserDTO(user);
		userRequest = new UserRequest(ConstsVar.CLIENT_NAME, ConstsVar.CLIENT_BIRTHDAY, ConstsVar.CLIENT_PHONE,
				ConstsVar.CLIENT_EMAIL);
		optionalUser = Optional.of(new User(ConstsVar.CLIENT_ID, ConstsVar.CLIENT_NAME, ConstsVar.CLIENT_BIRTHDAY,
				ConstsVar.CLIENT_PHONE, ConstsVar.CLIENT_EMAIL));
	}
}
