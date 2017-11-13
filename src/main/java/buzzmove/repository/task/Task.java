package buzzmove.repository.task;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import buzzmove.repository.address.Address;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date scheduledDate;

	private TaskStatus taskStatus;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id")
	private Address address;

	private String notes;

	public Task() {
	}

	public Task(String title, String firstName, String lastName, Date scheduledDate, TaskStatus taskStatus,
			Address address) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.scheduledDate = scheduledDate;
		this.taskStatus = taskStatus;
		this.address = address;
	}

	public Task(String title, String firstName, String lastName, Date scheduledDate, TaskStatus taskStatus,
			Address address, String notes) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.scheduledDate = scheduledDate;
		this.taskStatus = taskStatus;
		this.address = address;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String toString() {
		return this.title + " " + this.firstName + " " + this.lastName + " " + this.scheduledDate + " "
				+ this.taskStatus + " " + this.address + " " + this.notes;
	}
}
