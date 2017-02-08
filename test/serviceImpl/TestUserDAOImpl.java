package serviceImpl;

import org.junit.Test;

import entity.User;
import junit.framework.Assert;
import service.UserDAO;
import service.impl.UserDAOImpl;

public class TestUserDAOImpl {

	@Test
	public void testUserDAOImpl() {
		User user = new User(1, "zhangsan", "123456");
		UserDAO userDAO = new UserDAOImpl();
		Assert.assertEquals(true, userDAO.userLogin(user));
	}
}
