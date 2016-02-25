package hibertest;

import java.io.File;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;  

public class HiberQTest {

	final static Logger log = Logger.getLogger(HiberQTest.class);
	
	public static void main(String[] args) {
		log.debug("trying my first log");
		log.info("first info log");
		
		showCurrentFolder();
		Configuration cfg=new Configuration();  
		cfg.configure("hibernate.cfg.xml");  
		
		//creating seession factory object  
	    SessionFactory factory=cfg.buildSessionFactory();  
	      
	    //creating session object  
	    Session session=factory.openSession();  
	      
	    //creating transaction object  
	    /* Transaction t=session.beginTransaction();  
	          
	    Employee e1=new Employee();  
	    e1.setId(115);  
	    e1.setFirstName("sonoo");  
	    e1.setLastName("jaiswal");  
	      
	    session.persist(e1);//persisting the object  
	      
	    t.commit();//transaction is committed
	    */  
	    session.close();  
	      
	    System.out.println("successfully saved");  
		
	}
	
	private static void showCurrentFolder() {
		 File directory = new File (".");
		 try {
			 System.out.println ("Current directory's canonical path: " 
					 + directory.getCanonicalPath()); 
			 System.out.println ("Current directory's absolute  path: " 
					 + directory.getAbsolutePath());
		 }catch(Exception e) {
		 System.out.println("Exceptione is ="+e.getMessage());
		 }
	}

}
