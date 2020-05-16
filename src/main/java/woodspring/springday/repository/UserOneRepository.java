package woodspring.springday.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import woodspring.springday.model.User;
import woodspring.springday.model.UserOne;

public interface UserOneRepository extends MongoRepository<UserOne, String>{
	
	@Query("{ 'firstname' : ?0}")
	List<UserOne> findUserByFirstname(String firstname);
	
	@Query("{ 'lastname' : ?0}")
	List<UserOne> findUserByLastname(String lastname);
	
	@Query("{ 'firstname': ?0, 'lastname' : ?0}")
	List<UserOne> findFirstnameAndLastname(String firstname, String lastname);
	
	@Query("{ 'age' : {$gt: ?0, $lt: ?1  } }")
	List<UserOne> findUserByAgeBetween(int ageGT, int ageLt);
	
	@Query("{ 'salary' : {$gt: ?0, $lt: ?1  } }")
	List<UserOne> findUserBySalaryBetween(int ageGT, int ageLt);

	
	@Query("{ 'fistname' : { $regex: ?0 } }")
	List<UserOne>findUserByRegexFirstname(String regex);

	@Query("{ 'lastname' : { $regex: ?0 } }")
	List<UserOne>findUserByRegexLastname(String regex);
	
	@Query( value = "{}", fields = "{name : 1}")
	List<UserOne> findNameAndId();
	
	@Query(value = "{}", fields = "{_id : 0}")
	List<UserOne> findFirstnameAndAgeExcludId();

	List<UserOne> findByFirstname(String firstname);
	List<UserOne> findByFirstnameStartingWith(String regex);
	List<UserOne> findByFirstnameEndingWith(String regex);
	List<UserOne> findByFirstnameLikeOrderByAgeAsc(String firstname);
	
	List<UserOne> findByLastname(String lastname);
	List<UserOne> findByLastnameStartingWith(String regex);
	List<UserOne> findByLastnameEndingWith(String regex);
	List<UserOne> findByLastnameLikeOrderByAgeAsc(String lastname);
	
	List<UserOne> findByAgeBetween(int ageGT, int ageLt);
	List<UserOne> findBySalaryBetween(int ageGT, int ageLt);

}
