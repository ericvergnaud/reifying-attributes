package jdbc;

@SuppressWarnings("serial")
public class ConflictingTypeException extends ReifyingException {

	public ConflictingTypeException(String columnName, JdbcType existing, JdbcType conflicting) {
		super(buildMessage(columnName, existing, conflicting));
	}

	private static String buildMessage(String columnName, JdbcType existing, JdbcType conflicting) {
		return "Conflicting type for column: " + columnName + ", expected " + existing.toString()
				+ ", encountered " + conflicting.toString();
	}

}
