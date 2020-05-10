package woodspring.springday.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

import woodspring.springday.constant.MongoConst;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	private MongoClient mongoClient = null;
	@Override
	public MongoClient mongoClient() {
		if (mongoClient == null)  {
			mongoClient = new MongoClient( MongoConst.MongoDbHostIpName, 27017);
		}
		return mongoClient;
	}

	@Override
	protected String getDatabaseName() {
		return MongoConst.MongoDbName;
	}

	@Override
	protected String getMappingBasePackage() {
		return "woodspring.springday";
	}
}
