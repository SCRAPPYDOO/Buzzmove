package buzzmove.repository.adress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import buzzmove.repository.task.Task;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String adressLine;

	private String adressSecondLine;

	private String town;

	private String county;

	private String postcode;

	@OneToOne(mappedBy = "address")
	private Task task;

	public Address() {
	}

	public Address(String adressLine, String town, String county, String postcode) {
		super();
		this.adressLine = adressLine;
		this.town = town;
		this.county = county;
		this.postcode = postcode;
	}

	public Address(String adressLine, String adressSecondLine, String town, String county, String postcode) {
		super();
		this.adressLine = adressLine;
		this.adressSecondLine = adressSecondLine;
		this.town = town;
		this.county = county;
		this.postcode = postcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdressLine() {
		return adressLine;
	}

	public void setAdressLine(String adressLine) {
		this.adressLine = adressLine;
	}

	public String getAdressSecondLine() {
		return adressSecondLine;
	}

	public void setAdressSecondLine(String adressSecondLine) {
		this.adressSecondLine = adressSecondLine;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
