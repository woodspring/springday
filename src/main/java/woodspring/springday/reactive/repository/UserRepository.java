package woodspring.springday.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import woodspring.springday.model.User;
public interface UserRepository extends ReactiveMongoRepository<User, String>{

}
