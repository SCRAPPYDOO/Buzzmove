package buzzmove.repository;

import org.springframework.data.repository.CrudRepository;

import buzzmove.repository.task.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
	
}
