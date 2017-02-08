package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Student;
import service.StudentDAO;

public class StudentDAOImpl implements StudentDAO {

	@Override
	public List<Student> queryAllStudents() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		List<Student> list = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			hql = "from Student";
			Query query = session.createQuery(hql);
			list = query.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.commit();
			return list;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	@Override
	public Student queryStudentBySid(String sid) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Student student = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			student = (Student) session.get(Student.class, sid);
			transaction.commit();
			return student;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.commit();
			return student;
		} finally {
			if (transaction != null) {
				transaction = null;
			}

		}
	}

	@Override
	public boolean addStudent(Student s) {
		// TODO Auto-generated method stub
		// ����ѧ��ѧ��
		s.setSid(getNewSid());
		Transaction transaction = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(s);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.commit();
			return false;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	@Override
	public boolean updateStudent(Student s) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.update(s);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.commit();
			return false;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}

	@Override
	public boolean deleteStudent(String sid) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		// String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			Student student = (Student) session.get(Student.class, sid);
			session.delete(student);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.commit();
			return false;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}

	}

	/**
	 * ����ѧ����ѧ��
	 * 
	 * @return
	 */
	private String getNewSid() {
		Transaction transaction = null;
		String hql = "";
		String sid = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			// ��õ�ǰѧ���������
			hql = "select max(sid) from Student";
			Query query = session.createQuery(hql);
			sid = (String) query.uniqueResult();
			if (sid == null || "".equals(sid)) {
				// ��һ��Ĭ�ϵ������
				sid = "S0000001";
			} else {
				// ȡ��7λ
				String temp = sid.substring(1);
				// ת��������
				int i = Integer.parseInt(temp);
				i++;
				// �ٻ�ԭΪ�ַ���
				temp = String.valueOf(i);
				int len = temp.length();
				// �չ���λ
				for (int j = 0; j < 7 - len; j++) {
					temp = "0" + temp;
				}
				sid = "S" + temp;
			}
			transaction.commit();
			return sid;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.commit();
			return null;
		} finally {
			if (transaction != null) {
				transaction = null;
			}
		}
	}
}
