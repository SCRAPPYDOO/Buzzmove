package buzzmove.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import buzzmove.repository.TaskRepository;
import buzzmove.repository.task.Task;
import buzzmove.repository.task.TaskStatus;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getTasks() {
		return (List<Task>) taskRepository.findAll();
	}

	public Task updateTask(Task task) {
		return addTask(task);
	}

	public void deleteTask(int id) {
		taskRepository.delete(id);

	}

	public Task addTask(Task task) {
		validateTask(task);
		return taskRepository.save(task);
	}

	private void validateTask(Task task) {
		if (task.getFirstName() == null || task.getFirstName().trim().equals("")) {
			throwException("First name");
		}
		if (task.getLastName() == null || task.getLastName().trim().equals("")) {
			throwException("First name");
		}
		if (task.getScheduledDate() == null) {
			throwException("Scheduled Date");
		}
		if (task.getAddress() != null) {
			if (task.getAddress().getAdressLine() == null || task.getAddress().getAdressLine().trim().equals("")) {
				throwException("1st address line");
			}
			if (task.getAddress().getTown() == null || task.getAddress().getTown().trim().equals("")) {
				throwException("Town");
			}
			if (task.getAddress().getCounty() == null || task.getAddress().getCounty().trim().equals("")) {
				throwException("County");
			}
			if (task.getAddress().getPostcode() == null || task.getAddress().getPostcode().trim().equals("")) {
				throwException("Postcode");
			}
		}
	}

	private void throwException(String message) throws ValidationException {
		throw new ValidationException(message + " is required");
	}

	public List<Task> getTasksFilterByTaskStatus(TaskStatus taskStatus) {
		return taskRepository.findByTaskStatus(taskStatus);
	}
}
