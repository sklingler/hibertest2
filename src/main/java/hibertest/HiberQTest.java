package hibertest;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.Work;
import org.hibernate.type.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HiberQTest {

	final static Logger log = Logger.getLogger(HiberQTest.class);

	public static void main(String[] args) {
		HiberQTest app = new HiberQTest();
		app.runApp();
	}

	private void runApp() {
		log.debug("trying my first log");
		log.info("first info log");

		showCurrentFolder();
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		// creating session factory object
		SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		/*
		 * Transaction t=session.beginTransaction();
		 * 
		 * Employee e1=new Employee(); e1.setId(115); e1.setFirstName("sonoo");
		 * e1.setLastName("jaiswal");
		 * 
		 * session.persist(e1);//persisting the object
		 * 
		 * t.commit();//transaction is committed
		 */

		performQuery(session);
		session.close();

		log.info("successfully saved");
	}

	private static void showCurrentFolder() {
		File directory = new File(".");
		try {
			log.info("Current directory's canonical path: " + directory.getCanonicalPath());
			log.info("Current directory's absolute  path: " + directory.getAbsolutePath());
		} catch (Exception e) {
			System.out.println("Exceptione is =" + e.getMessage());
		}
	}

	private void performQuery(Session session) {
		// SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// Session session = sessionFactory.getCurrentSession();

		// Get All Employees
		// Transaction tx = session.beginTransaction();
		log.info("entered performQuery");
		// SQLQuery query = session.createSQLQuery("select * from aoproducts");
		SQLQuery query = session.createSQLQuery(
				"select id J4FrankId, name J4FrankName, startdate J4FrankDate, isinactiveflag J4FrankIsInactive from aoproducts");
		List<Object[]> rows = query.list();

		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List data = query.list();

		for (Object object : data) {
			Map row = (Map) object;
			System.out.print("id: " + row.get("id"));
			System.out.println(", name: " + row.get("name"));
		}
		/*
		 * Type types[] = query.getReturnTypes(); for(Type aType: types){
		 * log.info("returned type: " + aType); }
		 */

		/*
		 * String aliases[] = query.getReturnAliases(); log.info(
		 * "got aliases: " + aliases);
		 */
		log.info("got " + rows.size() + " rows");

		for (Object[] rowEntity : rows) {
			log.info("row start");
			for (Object entityCol : rowEntity) {
				log.info(" " + entityCol);
			}
		}

		Gson gson = new Gson();
		// String json = gson.toJson(rows);
		String json = gson.toJson(data);
		log.info("created JSON " + json);

		describe( session);
		log.info("exited performQuery");
	}

	private void describe(Session session) {

		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				DatabaseMetaData databaseMetaData = connection.getMetaData();
				writeTableInfo(databaseMetaData, "AoProducts");
				writeTableInfo(databaseMetaData, "vw_inv_poplacedby");
			}
		});
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
				log.info("column name: " + columnName + " type: " + columnType );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
