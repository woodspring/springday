package woodspring.springday.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import woodspring.springday.model.User;

@Component
public class UserWriterConverter implements Converter<User,DBObject> {

	@Override
	public DBObject convert(User user) {
		final DBObject dbObject = new BasicDBObject();
		dbObject.put("firstname", user.getFirstname() );
		dbObject.put("lastname", user.getLastname() );
		dbObject.put("age", user.getAge());
		if ( user.getEmailAddress() != null) {
			final DBObject emailDbObject = new BasicDBObject();
			emailDbObject.put("value", user.getEmailAddress().getValue());
			dbObject.put("email",  emailDbObject);
		}
		dbObject.removeField("_class");
		return dbObject;
	}

}
