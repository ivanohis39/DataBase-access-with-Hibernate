package jdbc;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		try {

			if (sessionFactory == null) {
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties());

				sessionFactory = configuration.buildSessionFactory(builder.build());
			}

		} catch (HibernateException ex) {
			System.out.println("Error al establecer la conexion " + ex);
		}

		return sessionFactory;
	}
}
