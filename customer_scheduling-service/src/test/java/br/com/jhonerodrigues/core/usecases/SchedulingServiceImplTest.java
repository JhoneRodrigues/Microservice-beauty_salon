package br.com.jhonerodrigues.core.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.mq.SchedulingProducer;
import br.com.jhonerodrigues.core.requests.JobId;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@SpringBootTest
class SchedulingServiceImplTest {
	
//	Scheduling properties
	private static final long PROF_ID = 1L;
	private static final StandardTimes TIME = StandardTimes.T09_11;
	private static final LocalDate DATE = LocalDate.parse("2000-02-02");
	private static final long SCHED_ID = 1L;
//	Job properties
	private static final int JOB_DURATION = 40;
	private static final double JOB_PRICE = 30.00;
	private static final String JOB_NAME = "Corte";
	private static final long JOB_ID = 1L;
//	User properties
	private static final String USER_PHONE = "(00) 90001-0002";
	private static final LocalDate USER_BIRH = LocalDate.parse("2000-02-01");
	private static final String USER_NAME = "Dura Junior";
	private static final long USER_ID = 1L;

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
		assertEquals(DATE, response.get(0).getCol_day());
	}

	@Test
	void FindByIdThenReturnAnSchedulingDTO() {
		when(schedulingRepository.findById(anyLong())).thenReturn(scheduling);
		
		SchedulingDTO response = schedulingService.findById(SCHED_ID);
		
		assertNotNull(response);
		assertEquals(SchedulingDTO.class, response.getClass());
		assertEquals(SCHED_ID, response.getId());
		assertEquals(DATE, response.getCol_day());
	}
	
	@Test
	void whenFindByIdThenReturnAnResourceNotFoundException() {
		when(schedulingRepository.findById(anyLong())).thenThrow(new ResourceNotFoundException(SCHED_ID));
		
		try {
			schedulingService.findById(SCHED_ID);
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
		
		SchedulingDTO response = schedulingService.InsertSchedulingByUserId(USER_ID, schedulingRequest);
		
		assertNotNull(response);
		assertNotNull(response.getJobs());

		assertEquals(SchedulingDTO.class, response.getClass());
		assertEquals(response.getJobs().size(), 1);
		assertEquals(SCHED_ID, response.getId());
		assertEquals(USER_ID, response.getClient_id());
		assertEquals(DATE, response.getCol_day());		
	}
	
	private void startUser() {
		jobDTO = new JobDTO(JOB_ID, JOB_NAME, JOB_PRICE, JOB_DURATION);
		jobId = new JobId(JOB_ID);
		
		scheduling = new Scheduling(SCHED_ID, DATE, TIME, PROF_ID, USER_ID);
		scheduling.getJobs().add(job);
		
		schedulingDTO = new SchedulingDTO(scheduling);
		schedulingRequest = new SchedulingRequest(DATE, TIME, PROF_ID, null);
		schedulingRequest.setJobs(Arrays.asList(jobId));
		
		user = new User(USER_ID, USER_NAME, USER_BIRH, USER_PHONE);
		userDTO = new UserDTO(USER_ID, USER_NAME, USER_BIRH, USER_PHONE);
 	}
}
