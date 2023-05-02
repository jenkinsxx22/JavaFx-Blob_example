package application.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import application.model.Photos;
 

 
public class HIbernateUtil {
	  private static SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml");

	                // Hibernate settings equivalent to hibernat]e.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER,"oracle.jdbc.driver.OracleDriver");
	                settings.put(Environment.URL,"jdbc:oracle:thin:@localhost:1521:xe");
	                settings.put(Environment.USER, "mdap");
	                settings.put(Environment.PASS, "mdap");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle8iDialect");
	                settings.put(Environment.HBM2DDL_AUTO, "create-only");


	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


	                configuration.setProperties(settings);

	                configuration.addAnnotatedClass(Photos.class);

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
}