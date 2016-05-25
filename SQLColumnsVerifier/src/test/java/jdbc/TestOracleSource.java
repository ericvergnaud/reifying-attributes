package jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcColumn;
import jdbc.JdbcSource;

import org.junit.Before;
import org.junit.Test;

public class TestOracleSource {

	JdbcSource ds;
	
	@Before
	public void before() throws SQLException {
		// replace the below with your own connection settings
		ds = new OracleSource("jdbc:oracle:thin:@//192.168.1.30:1521/orcl", "scott", "oracle");
	}
	
	
	@Test
	public void testDataSource() throws SQLException {
		try (Connection cnx = ds.getDs().getConnection()) {
		}
	}
	
	@Test
	public void testFetchColumns() throws SQLException {
		List<JdbcColumn> list = ds.fetchColumns();
		assertTrue(list.size()>0);
		JdbcColumn.checkReified(list);
	}
	
	
}
