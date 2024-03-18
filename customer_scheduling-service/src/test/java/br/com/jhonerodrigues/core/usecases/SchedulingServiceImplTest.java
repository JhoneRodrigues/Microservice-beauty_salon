package br.com.jhonerodrigues.core.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jhonerodrigues.ConstsVar;
import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.mq.SchedulingProducer;
import br.com.jhonerodrigues.core.requests.JobId;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@SpringBootTest
class SchedulingServiceImplTest {

	@InjectMocks
	private SchedulingServiceImpl schedulingService;

	@Mock
	private SchedulingRepository schedulingRepository;

	@Mock
	private UserService userService;

	@Mock
	private JobService jobService;

	@Mock
	private SchedulingProducer producer;

	private Scheduling scheduling;
	private SchedulingDTO schedulingDTO;
	private SchedulingRequest schedulingRequest;

	private Job job;
	private JobDTO jobDTO;
	private JobId jobId;

	private User user;
	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void FindAllThenReturnAnListOfSchedulingsDTO() {
		when(schedulingRepository.findAll()).thenReturn(List.of(scheduling));
		
		List<SchedulingDTO> response = schedulingService.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(SchedulingDTO.class, response.get(0).getClass());
		assertEquals(ConstsVar.SCHEDULING_DAY, response.get(0).getCol_day());
	}

	@Test
	void FindByIdThenReturnAnSchedulingDTO() {
		when(schedulingRepository.findById(anyLong())).thenReturn(scheduling);
		
		SchedulingDTO response = schedulingService.findById(ConstsVar.SCHEDULING_ID);
		
		assertNotNull(response);
		assertEquals(SchedulingDTO.class, response.getClass());
		assertEquals(ConstsVar.SCHEDULING_ID, response.getId());
		assertEquals(ConstsVar.SCHEDULING_DAY, response.getCol_day());
	}

	@Test
	void whenFindByIdThenReturnAnResourceNotFoundException() {
		when(schedulingRepository.findById(anyLong()))
				.thenThrow(new ResourceNotFoundException(ConstsVar.SCHEDULING_ID));
		
		try {
			schedulingService.findById(ConstsVar.SCHEDULING_ID);
		} catch(Exception ex) {
			assertEquals(ResourceNotFoundException.class, ex.getClass());
		}
	}

	@Test
	void InsertSchedulingByUserIdThenReturnAnSchedulingDTO() {
		when(jobService.findById(anyLong())).thenReturn(jobDTO);
		when(userService.findById(anyLong())).thenReturn(userDTO);
		when(schedulingRepository.insert(any())).thenReturn(scheduling);
		when(userService.update(anyLong(), any())).thenReturn(userDTO);
		doNothing().when(producer).sendScheduling(any(), any());
		
		SchedulingDTO response = schedulingService.InsertSchedulingByUserId(ConstsVar.CLIENT_ID, schedulingRequest);
		
		assertNotNull(response);
		assertNotNull(response.getJobs());

		assertEquals(SchedulingDTO.class, response.getClass());
		assertEquals(response.getJobs().size(), 1);
		assertEquals(ConstsVar.SCHEDULING_ID, response.getId());
		assertEquals(ConstsVar.CLIENT_ID, response.getClient_id());
		assertEquals(ConstsVar.SCHEDULING_DAY, response.getCol_day());		
	}

	private void startUser() {
		jobDTO = new JobDTO(ConstsVar.JOB_ID, ConstsVar.JOB_NAME, ConstsVar.JOB_PRICE, ConstsVar.JOB_DURATION);
		jobId = new JobId(ConstsVar.JOB_ID);

		scheduling = new Scheduling(ConstsVar.SCHEDULING_ID, ConstsVar.SCHEDULING_DAY, ConstsVar.SCHEDULING_TIME,
				ConstsVar.SCHEDULING_PROF_ID, ConstsVar.CLIENT_ID);
		scheduling.getJobs().add(job);

		schedulingDTO = new SchedulingDTO(scheduling);
		schedulingRequest = new SchedulingRequest(ConstsVar.SCHEDULING_DAY, ConstsVar.SCHEDULING_TIME,
				ConstsVar.SCHEDULING_PROF_ID, null);
		schedulingRequest.setJobs(Arrays.asList(jobId));

		user = new User(ConstsVar.CLIENT_ID, ConstsVar.CLIENT_NAME, ConstsVar.CLIENT_BIRTHDAY, ConstsVar.CLIENT_PHONE);
		userDTO = new UserDTO(user);
	}
}
