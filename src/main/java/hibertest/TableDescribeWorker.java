package hibertest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;

public class TableDescribeWorker implements Work {
	private String tableName = null;
	private Logger log = null;
	
	TableDescribeWorker(String tableName, Logger log){
		this.tableName = tableName;
		this.log = log;
	}
	
	public void execute(Connection connection) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		writeTableInfo(databaseMetaData, "AoProducts");
	}

	private void writeTableInfo(DatabaseMetaData databaseMetaData, String tableName) {
		String catalog = null;
		String schemaPattern = null;
		String columnNamePattern = null;

		ResultSet result;
		try {
			result = databaseMetaData.getColumns(catalog, schemaPattern, tableName, columnNamePattern);
			
			log.info("column info for table: " + tableName);
			while (result.next()) {
				String columnName = result.getString(4);
				int columnType = result.getInt(5);
				log.info("    column name: " + columnName + " type: " + columnType );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
