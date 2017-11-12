package buzzmove.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import buzzmove.repository.task.Task;
import buzzmove.service.TaskService;


@RestController
@CrossOrigin
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/task")
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/task")
	public Task addTask(@RequestBody Task task) {
		return taskService.addTask(task);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/task")
	public Task updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/task/{id}")
	public void deleteTask(@PathVariable int id) {
		taskService.deleteTask(id);
	}
}
