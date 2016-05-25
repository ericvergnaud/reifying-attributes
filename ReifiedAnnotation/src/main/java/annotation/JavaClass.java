package annotation;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class JavaClass {

	public static void checkReified(Collection<Class<?>> classes) {
		Map<String,Field> reified = new HashMap<>();
		classes.forEach((c)->
			checkReified(reified, c));
	}
	
	static void checkReified(Map<String,Field> reified, Class<?> klass) {
		if(klass.isAnnotationPresent(Reified.class)) {
			for(Field field : klass.getDeclaredFields())
				checkReified(reified, field);
		} else {
			for(Field field : klass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Reified.class))
					checkReified(reified, field);
			}
		}
	}
	
	
	static void checkReified(Map<String,Field> reified, Field field) {
		Field existing = reified.get(field.getName());
		if(existing==null)
			reified.put(field.getName(), field);
		else if(!existing.getType().equals(field.getType()))
			throw new ConflictingTypeException(existing, field);
	}
	
}
