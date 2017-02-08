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
		// �������ö���
		Configuration config = new Configuration().configure();
		// ��������ע�����
		ServiceRegistry standardServiceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// ����sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(standardServiceRegistry);
		// ����session����
		Session session = sessionFactory.getCurrentSession();
		// ����SchemaExoprt����
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);

	}

	@Test
	public void testSaveStudents() {
		// �������ö���
		Configuration config = new Configuration().configure();
		// ��������ע�����
		ServiceRegistry standardServiceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// ����sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(standardServiceRegistry);
		// ����session����
		Session session = sessionFactory.getCurrentSession();
		// �����������
		Transaction transaction = session.beginTransaction();

		Student student1 = new Student("S0000001", "������", "��", new Date(), "�䵱ɽ");
		Student student2 = new Student("S0000002", "����", "��", new Date(), "�һ���");
		Student student3 = new Student("S0000003", "����", "Ů", new Date(), "�һ���");

		session.save(student1);
		session.save(student2);
		session.save(student3);

		transaction.commit();
		sessionFactory.close();
	}
}
