package woodspring.springday.event;


import java.lang.reflect.Field;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import woodspring.springday.annotation.CascadeSave;

public class CascadeCallback implements ReflectionUtils.FieldCallback{

	private Object source;
	private MongoOperations mongoOperations;
	
	
	
	public CascadeCallback(final Object source, final  MongoOperations mongoOperations) {
		this.source = source;
		this.mongoOperations = mongoOperations;
	}



	@Override
	public void doWith( final Field field) throws IllegalArgumentException, IllegalAccessException {
		ReflectionUtils.makeAccessible( field);
		if ( field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent( CascadeSave.class)) {
			final Object fieldValue = field.get(getSource());
			if ( fieldValue != null) {
				final FieldCallback callback = new FieldCallback();
				ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
				getMongoOperations().save( fieldValue);
			}
		}
		
	}



	public Object getSource() {
		return source;
	}



	public void setSource(Object source) {
		this.source = source;
	}



	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}



	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

}
