package jdbc;

@SuppressWarnings("serial")
public class ConflictingLengthException extends ReifyingException {

	public ConflictingLengthException(String columnName, JdbcType existing, JdbcType conflicting) {
		super(buildMessage(columnName, existing, conflicting));
	}

	private static String buildMessage(String columnName, JdbcType existing, JdbcType conflicting) {
		return "Conflicting length for column: " + columnName + ", expected " + existing.toString()
				+ ", encountered " + conflicting.toString();
	}

}
