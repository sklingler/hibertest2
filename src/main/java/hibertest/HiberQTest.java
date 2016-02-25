package hibertest;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;  

public class HiberQTest {

	public static void main(String[] args) {
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
