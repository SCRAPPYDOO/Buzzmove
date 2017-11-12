package buzzmove.servicetest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import buzzmove.BuzzmoveTest;
import buzzmove.repository.TaskRepository;
import buzzmove.repository.task.Task;
import buzzmove.service.TaskService;

@RunWith(SpringRunner.class)
public class TaskServiceTest extends BuzzmoveTest {
	
	@TestConfiguration
	static class TaskServiceTestContextConfiguration {

		@Bean
		public TaskService taskService() {
			return new TaskService();
		}
	}

	@Autowired
	private TaskService taskService;

	@MockBean
	private TaskRepository taskRepository;

	@Before
	public void setUp() {
		Task task = this.getTask();
		task.setId(1);
		Mockito.when(taskRepository.save(task)).thenReturn(task);
		Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
	}

	@Test
	public void addTaskTest() {
		Task t = this.getTask();
		t.setScheduledDate(null);	
		try {
			taskService.addTask(t);
		} catch(ValidationException e) {
			assertEquals("Scheduled Date is required", e.getMessage());
		}
		t.setScheduledDate(new Date());
		assertEquals(1, ((List<Task>)taskRepository.findAll()).size());
	}
}
