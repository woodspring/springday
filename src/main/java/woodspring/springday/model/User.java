package woodspring.springday.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.querydsl.core.annotations.QueryEntity;

import woodspring.springday.annotation.CascadeSave;



@QueryEntity
@Document
@CompoundIndexes({ @CompoundIndex(name = "email_age", def = "{'email.id' : 1, 'age': 1}") })
public class User {

	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String firstname;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String lastname;
	@Indexed(direction = IndexDirection.ASCENDING)
	private Integer age;
	
	@DBRef
	@Field("email")
	@CascadeSave
	private EmailAddress emailAddress;
	
	@Transient
	private Integer yyyymmddOfBirth;
	
	
	public User() {}


	public User(String firstname, String lastname, Integer age) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}
	
	 @PersistenceConstructor
	 public User(final String firstname, final String lastname, @Value("#root.age ?: 0") final Integer age, final EmailAddress emailAddress) {
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.age = age;
	        this.emailAddress = emailAddress;
	    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public EmailAddress getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Integer getYyyymmddOfBirth() {
		return yyyymmddOfBirth;
	}


	public void setYyyymmddOfBirth(Integer yyyymmddOfBirth) {
		this.yyyymmddOfBirth = yyyymmddOfBirth;
	}
	
	
}
