package woodspring.springday.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Department")
public class Department {
	@Id
	private String depId;
	@Indexed(name = "deptName")
	private String depName;
	private String description;
	//@DBRef
	private List<UserOne> employees;
	public Department(String name, String description) {
		super();
		this.depName = name;
		this.description = description;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String name) {
		this.depName = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UserOne> getEmployees() {
		return employees;
	}
	public void setEmployees(List employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "Department [depId=" + depId + ", depName=" + depName + ", description=" + description + "]";
	}

}
