package jdbc;

public class JdbcType {

	String typeName;
	Integer length;

	public JdbcType(String typeName, Integer length) {
		this.typeName = typeName;
		this.length = length;
	}
	
	@Override
	public String toString() {
		return typeName + length==null ? "" : "(" + length + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==this)
			return true;
		else if(!(obj instanceof JdbcType))
			return false;
		JdbcType other = (JdbcType)obj;
		return this.typeName.equals(other.typeName)
				&& (this.length==null ? other.length==null : this.length.equals(other.length));
	}
	
}
