package reflect;

import java.lang.reflect.Field;

@SuppressWarnings("serial")
public class ConflictingTypeException extends ReifyingException {

	public ConflictingTypeException(Field existing, Field conflicting) {
		super(buildMessage(existing, conflicting));
	}

	private static String buildMessage(Field existing, Field conflicting) {
		return "Conflicting type for field: " + existing.getName() + ", expected " + existing.getType().toString()
				+ ", encountered " + conflicting.getType().toString();
	}

}
