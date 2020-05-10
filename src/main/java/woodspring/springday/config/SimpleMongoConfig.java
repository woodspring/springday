package woodspring.springday.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import woodspring.springday.constant.MongoConst;

@Configuration
public class SimpleMongoConfig {
	
	@Bean
	public MongoClient mongo() { 
		return new MongoClient(MongoConst.MongoDbHostIpName);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate( mongo(), MongoConst.MongoDbName);
	}

}
