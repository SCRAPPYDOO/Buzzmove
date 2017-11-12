package buzzmove;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import buzzmove.repository.address.Address;
import buzzmove.repository.task.Task;
import buzzmove.repository.task.TaskStatus;

public class BuzzmoveTest {
	protected static final ObjectMapper mapper = new ObjectMapper();

	protected Address getAddress() {
		return new Address("1/12", "London", "Midterran", "SW18");
	}

	protected Task getTask() {
		return new Task("Mr", "Bill", "Gates", new Date(), TaskStatus.OPEN, getAddress(), "simple second note");
	}

	protected String getTaskAsJson() throws JsonProcessingException {
		return mapper.writeValueAsString(getTask());
	}
}
