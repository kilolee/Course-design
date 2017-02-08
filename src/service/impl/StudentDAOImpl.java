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
		// 设置学生学号
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
	 * 生成学生的学号
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
			// 获得当前学生的最大编号
			hql = "select max(sid) from Student";
			Query query = session.createQuery(hql);
			sid = (String) query.uniqueResult();
			if (sid == null || "".equals(sid)) {
				// 给一个默认的最大编号
				sid = "S0000001";
			} else {
				// 取后7位
				String temp = sid.substring(1);
				// 转换成数字
				int i = Integer.parseInt(temp);
				i++;
				// 再还原为字符串
				temp = String.valueOf(i);
				int len = temp.length();
				// 凑够七位
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
