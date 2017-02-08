package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 创建会话工厂实例
 * 
 * @author ASUS
 *
 */
public class MyHibernateSessionFactory {
	// 会话工厂属性
	private static SessionFactory sessionFactory;

	/**
	 * 构造方法私有化。保证单例模式
	 */
	public MyHibernateSessionFactory() {
		// super();
		// TODO Auto-generated constructor stub
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// 创建配置对象
			Configuration config = new Configuration().configure();
			// 创建服务注册对象
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
					.buildServiceRegistry();
			// 创建sessionFactory
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
}
