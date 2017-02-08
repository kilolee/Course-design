package service;

import entity.User;

/**
 * 用户业务逻辑接口
 * 
 * @author ASUS
 *
 */
public interface UserDAO {

	// 用户登陆方法
	public boolean userLogin(User user);
}
