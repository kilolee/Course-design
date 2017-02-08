package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.User;
import service.UserDAO;

/**
 * UserDao的实现类
 * 
 * @author ASUS
 *
 */
public class UserDAOImpl implements UserDAO {

	@Override
	public boolean userLogin(User user) {
		// TODO Auto-generated method stub
		// 事务对象
		Transaction transaction = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			hql = "from User where username = ? and password = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getPassword());
			List<Object> list = query.list();
			transaction.commit();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;

		} finally {
			if (transaction != null) {
				// transaction.commit();
				transaction = null;
			}

		}
	}

}
