package reflect;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class JavaField {

	public static void checkReified(Collection<Field> fields) {
		Map<String,Field> reified = new HashMap<>();
		fields.forEach((f)->
			checkReified(reified, f));
	}
	
	static void checkReified(Map<String,Field> reified, Field field) {
		Field existing = reified.get(field.getName());
		if(existing==null)
			reified.put(field.getName(), field);
		else if(!existing.getType().equals(field.getType()))
			throw new ConflictingTypeException(existing, field);
	}
	
}
