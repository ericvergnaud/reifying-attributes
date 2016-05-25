package jdbc;

import java.sql.SQLException;
import java.util.Arrays;

import jdbc.ConflictingLengthException;
import jdbc.ConflictingTypeException;
import jdbc.JdbcColumn;

import org.junit.Test;

public class TestJdbcColumn {

	@Test
	public void testReifiedOneColumn() throws SQLException {
		JdbcColumn col = new JdbcColumn("my_table", "my_column", "varchar", 32);
		JdbcColumn.checkReified(Arrays.asList(col));
	}

	@Test
	public void testReifiedTwoColumns() throws SQLException {
		JdbcColumn col1 = new JdbcColumn("my_table", "my_column", "varchar", 32);
		JdbcColumn col2 = new JdbcColumn("my_table", "my_column2", "varchar", 32);
		JdbcColumn.checkReified(Arrays.asList(col1, col2));
	}
	
	@Test(expected=ConflictingTypeException.class)
	public void testConflictingTypes() throws SQLException {
		JdbcColumn col1 = new JdbcColumn("my_table1", "my_column", "varchar", 32);
		JdbcColumn col2 = new JdbcColumn("my_table2", "my_column", "int", null);
		JdbcColumn.checkReified(Arrays.asList(col1, col2));
	}

	@Test(expected=ConflictingLengthException.class)
	public void testConflictingLengths() throws SQLException {
		JdbcColumn col1 = new JdbcColumn("my_table1", "my_column", "varchar", 32);
		JdbcColumn col2 = new JdbcColumn("my_table2", "my_column", "varchar", 16);
		JdbcColumn.checkReified(Arrays.asList(col1, col2));
	}


}
