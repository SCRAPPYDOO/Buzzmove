package buzzmove.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import buzzmove.repository.task.Task;
import buzzmove.repository.task.TaskStatus;

public interface TaskRepository extends CrudRepository<Task, Integer> {

	public List<Task> findByTaskStatus(TaskStatus taskStatus);
}
