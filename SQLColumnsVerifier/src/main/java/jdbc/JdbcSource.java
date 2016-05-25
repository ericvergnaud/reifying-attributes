package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public abstract class JdbcSource {

	public static interface RowReader<T> {
		T readRow(ResultSet rs) throws SQLException;
	}
	
	DataSource ds;
	
	public JdbcSource(DataSource ds) throws SQLException {
		this.ds = ds;
	}
	
	public DataSource getDs() {
		return ds;
	}

	public List<JdbcColumn> fetchColumns() throws SQLException {
		return fetchMany(getFetchColumnsQuery(), this::readColumn);
	}
	
	protected abstract String getFetchColumnsQuery() throws SQLException;
	protected abstract JdbcColumn readColumn(ResultSet rs) throws SQLException;
	
	public <T> List<T> fetchMany(String sql, RowReader<T> reader) throws SQLException {
		try(Connection cnx = ds.getConnection()) {
			return fetchMany(cnx, sql, reader);
		}
	}
	
	public <T> T fetchOne(String sql, RowReader<T> reader) throws SQLException {
		try(Connection cnx = ds.getConnection()) {
			return fetchOne(cnx, sql, reader);
		}
	}


	private <T> List<T> fetchMany(Connection cnx, String sql, RowReader<T> reader) throws SQLException {
		try(PreparedStatement stmt = cnx.prepareStatement(sql)) {
			return fetchMany(stmt, reader);
		}
	}

	private <T> T fetchOne(Connection cnx, String sql, RowReader<T> reader) throws SQLException {
		try(PreparedStatement stmt = cnx.prepareStatement(sql)) {
			return fetchOne(stmt, reader);
		}
	}

	private <T> List<T> fetchMany(PreparedStatement stmt, RowReader<T> reader) throws SQLException {
		try(ResultSet rs = stmt.executeQuery()) {
			return fetchMany(rs, reader);
		}
	}

	private <T> T fetchOne(PreparedStatement stmt, RowReader<T> reader) throws SQLException {
		try(ResultSet rs = stmt.executeQuery()) {
			if(rs.next())
				return reader.readRow(rs);
			else
				return null;
		}
	}

	private <T> List<T> fetchMany(ResultSet rs, RowReader<T> reader) throws SQLException {
		List<T> list = new ArrayList<>();
		while(rs.next()) {
			T o = reader.readRow(rs);
			list.add(o);
		}
		return list;
	}


	
}
