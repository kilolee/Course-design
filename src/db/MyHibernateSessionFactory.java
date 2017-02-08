package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * �����Ự����ʵ��
 * 
 * @author ASUS
 *
 */
public class MyHibernateSessionFactory {
	// �Ự��������
	private static SessionFactory sessionFactory;

	/**
	 * ���췽��˽�л�����֤����ģʽ
	 */
	public MyHibernateSessionFactory() {
		// super();
		// TODO Auto-generated constructor stub
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// �������ö���
			Configuration config = new Configuration().configure();
			// ��������ע�����
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
					.buildServiceRegistry();
			// ����sessionFactory
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
}
