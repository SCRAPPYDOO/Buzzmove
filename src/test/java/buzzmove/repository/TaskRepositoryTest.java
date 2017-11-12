package buzzmove.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import buzzmove.BuzzmoveTest;
import buzzmove.repository.task.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest extends BuzzmoveTest{

	@Autowired
	private TaskRepository taskRepository;
	
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
