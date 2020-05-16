package woodspring.springday.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import woodspring.springday.model.Department;
import woodspring.springday.model.UserOne;

@Repository
public interface DepartmentRepository extends  MongoRepository<UserOne, String> {
	
	@Query(value="{ 'employees.firstname': ?0}", fields = "{'employees' : 0}")
	Department findDepartmentByEmployeeName(String firstname);
	@Query(value="{ 'employees.firstname': ?0, 'employees.lastname': ?1}", fields = "{'employees' : 0}")
	Department findDepartmentByFirstnameLastname(String firstname, String lastname);

	List<UserOne> findDepartmentByDepName(String depName);

}
