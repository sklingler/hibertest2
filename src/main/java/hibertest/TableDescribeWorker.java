package hibertest;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;

public class TableDescribeWorker implements Work {
	private String tableName = null;
	
	TableDescribeWorker(String tableName){
		this.tableName = tableName;
	}
	
	public void execute(Connection connection) throws SQLException {
		
	}
}
