package woodspring.springday.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import woodspring.springday.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User>  {

	@Query("{ 'firstname' : ?0}")
	List<User> findUserByFirstname(String firstname);
	
	@Query("{ 'lastname' : ?0}")
	List<User> findUserByLastname(String lastname);
	
	@Query("{ 'firstname': ?0, 'lastname' : ?0}")
	List<User> findFirstnameAndLastname(String firstname, String lastname);
	
	@Query("{ 'age' : {$gt: ?0, $lt: ?1  } }")
	List<User> findUserByAgeBetween(int ageGT, int ageLt);
	
	@Query("{ 'fistname' : { $regex: ?0 } }")
	List<User>findUserByRegexFirstname(String regex);

	@Query("{ 'lastname' : { $regex: ?0 } }")
	List<User>findUserByRegexLastname(String regex);
	
	@Query( value = "{}", fields = "{name : 1}")
	List<User> findNameAndId();
	
	@Query(value = "{}", fields = "{_id : 0}")
	List<User> findFirstnameAndAgeExcludId();

	List<User> findByFirstname(String firstname);
	List<User> findByFirstnameStartingWith(String regex);
	List<User> findByFirstnameEndingWith(String regex);
	List<User> findByFirstnameLikeOrderByAgeAsc(String firstname);
	
	List<User> findByLastname(String lastname);
	List<User> findByLastnameStartingWith(String regex);
	List<User> findByLastnameEndingWith(String regex);
	List<User> findByLastnameLikeOrderByAgeAsc(String lastname);
	
	List<User> findByAgeBetween(int ageGT, int ageLt);
}
