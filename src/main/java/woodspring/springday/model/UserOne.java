package woodspring.springday.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class UserOne {
	
	@Id
	private String userId;
	private String firstname;
	private String lastname;
	private int age;
	private double salary;
	public UserOne(String firstname, String lastname, int age, double salary) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.salary = salary;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "UserOne [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age
				+ ", salary=" + salary + "]";
	}

}
