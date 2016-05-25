package jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JdbcColumn {

	public static void checkReified(Collection<JdbcColumn> columns) {
		Map<String,JdbcType> reified = new HashMap<>();
		columns.forEach((c)->
			c.checkReified(reified));
	}
	
	String tableName;
	String columnName;
	JdbcType dataType;

	public JdbcColumn(String tableName, String columnName, String dataType, Integer dataLength) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.dataType = new JdbcType(dataType, dataLength);
	}
	
	private void checkReified(Map<String, JdbcType> reified) {
		JdbcType existing = reified.get(columnName);
		if(existing==null)
			reified.put(columnName, dataType);
		else if(!existing.equals(dataType)) {
			if(!dataType.typeName.equals(existing.typeName))
				throw new ConflictingTypeException(columnName, existing, dataType);
			else
				throw new ConflictingLengthException(columnName, existing, dataType);
		}
	}
	
}


