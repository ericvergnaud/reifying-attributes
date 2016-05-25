package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class OracleSource extends JdbcSource {

	public OracleSource(String url, String user, String password) throws SQLException {
		super(newDataSource(url, user, password));
	}

	private static DataSource newDataSource(String url, String user, String password) throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(password);	
		return ds;
	}
	
	@Override
	protected String getFetchColumnsQuery() throws SQLException {
		return "select "
				+ "table_name, column_name, data_type, data_length "
				+ "from SYS.DBA_TAB_COLS "
				+ "where owner = 'SCOTT'";
	}

	@Override
	protected JdbcColumn readColumn(ResultSet rs) throws SQLException {
		Integer dataLength = rs.getInt("data_length");
		if(rs.wasNull())
			dataLength = null;
		return new JdbcColumn(
				rs.getString("table_name"),
				rs.getString("column_name"),
				rs.getString("data_type"),
				dataLength);
	}

}
