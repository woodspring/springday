package woodspring.springday.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import woodspring.springday.model.Action;

public interface ActionRepository extends MongoRepository<Action, String> {

}
