package woodspring.springday.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import woodspring.springday.constant.MongoConst;


@Configuration
@EnableReactiveMongoRepositories(basePackages = "woodspring.springday.reactive.repository")
public class MongoReactiveConfig  extends AbstractReactiveMongoConfiguration{

	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		
		return MongoConst.MongoDbReactive;
	}

}
