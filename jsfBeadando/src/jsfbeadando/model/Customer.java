package jsfbeadando.model;

public class Customer {
	long id;
	String firstName;
	String cim;
	String company;
	String emplNumber;
	double salary;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCim() {
		return cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmplNumber() {
		return emplNumber;
	}

	public void setEmplNumber(String emplNumber) {
		this.emplNumber = emplNumber;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", cim=" + cim + ", company=" + company
				+ ", emplNumber=" + emplNumber + ", salary=" + salary + "]";
	}

}
