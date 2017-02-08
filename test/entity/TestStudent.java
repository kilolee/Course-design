package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TestStudent {

	@Test
	public void testSchemaExport() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry standardServiceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(standardServiceRegistry);
		// 创建session对象
		Session session = sessionFactory.getCurrentSession();
		// 创建SchemaExoprt对象
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);

	}

	@Test
	public void testSaveStudents() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry standardServiceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(standardServiceRegistry);
		// 创建session对象
		Session session = sessionFactory.getCurrentSession();
		// 创建事务对象
		Transaction transaction = session.beginTransaction();

		Student student1 = new Student("S0000001", "张三丰", "男", new Date(), "武当山");
		Student student2 = new Student("S0000002", "郭靖", "男", new Date(), "桃花岛");
		Student student3 = new Student("S0000003", "黄蓉", "女", new Date(), "桃花岛");

		session.save(student1);
		session.save(student2);
		session.save(student3);

		transaction.commit();
		sessionFactory.close();
	}
}
