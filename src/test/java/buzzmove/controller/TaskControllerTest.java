package buzzmove.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import buzzmove.repository.adress.Address;
import buzzmove.repository.task.Task;
import buzzmove.repository.task.TaskStatus;
import buzzmove.service.TaskService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	
	protected static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	private Address getAddress() {
		return new Address("1/12", "London", "Midterran", "SW18");
	}
	
	private Task getTask() {
		return new Task("Mr", "Bill", "Gates", new Date(), TaskStatus.OPEN, getAddress(), "simple second note");
	}
	
	private String getTaskAsJson() throws JsonProcessingException {
		return mapper.writeValueAsString(getTask());
	}

	@Before
	public void setUp() {
		List<Task> tasks = Arrays.asList(
				getTask(),
				new Task("Mss", "Steve", "Jobs", new Date(), TaskStatus.IN_PROGRESS, getAddress(), "simple second note from Steve Jobs")
		);

		Mockito.when(taskService.getTasks()).thenReturn(tasks);
	}
	
	@Test
	public void getTasks() throws Exception {
		this.mockMvc.perform(get("/task")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Bill")));
		
		this.mockMvc.perform(get("/task")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Jobs")));
	}
	
	@Test
	public void addTask() throws JsonProcessingException, Exception {
		this.mockMvc.perform(post("/task").contentType(MediaType.APPLICATION_JSON).content(getTaskAsJson())).andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void updateTask() throws Exception {
		this.mockMvc.perform(put("/task").contentType(MediaType.APPLICATION_JSON).content(getTaskAsJson())).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteTask() throws Exception {
		this.mockMvc.perform(delete("/task/1")).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
}
