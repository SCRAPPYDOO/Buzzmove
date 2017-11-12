package buzzmove.repository;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import buzzmove.repository.adress.Address;
import buzzmove.repository.task.Task;
import buzzmove.repository.task.TaskStatus;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepository;

	private Task getTask() {
		Address address = new Address("1/12", "London", "Midterran", "SW18");
		return new Task("Mr", "Bill", "Gates", new Date(), TaskStatus.OPEN, address, "simple second note");
	}
	
	@Test
	public void addTask() {
		assertEquals(0, ((List<Task>)taskRepository.findAll()).size());
		Task savedTask = taskRepository.save(getTask());
		assertEquals(1, ((List<Task>)taskRepository.findAll()).size());
		assertEquals(savedTask, taskRepository.findOne(savedTask.getId()));
		assertEquals("London", taskRepository.findOne(savedTask.getId()).getAddress().getTown());
	}

	@Test
	public void deleteTask() {
		Task savedTask = taskRepository.save(getTask());
		assertEquals(1, ((List<Task>)taskRepository.findAll()).size());
		taskRepository.delete(savedTask.getId());
		assertEquals(0, ((List<Task>)taskRepository.findAll()).size());
		assertEquals(null, taskRepository.findOne(savedTask.getId()));
	}

	@Test
	public void getTasks() {
		taskRepository.save(getTask());
		taskRepository.save(getTask());
		taskRepository.save(getTask());
		assertEquals(3, ((List<Task>)taskRepository.findAll()).size());
	}

	@Test
	public void updateTask() {
		Task savedTask = taskRepository.save(getTask());
		assertEquals("Bill", taskRepository.findOne(savedTask.getId()).getFirstName());
		savedTask.setFirstName("Steve");
		taskRepository.save(savedTask);
		assertEquals("Steve", taskRepository.findOne(savedTask.getId()).getFirstName());
	}
}
