package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.User;
import service.UserDAO;
import service.impl.UserDAOImpl;

/**
 * 用户Action类
 * 
 * @author ASUS
 *
 */
public class UserAction extends SuperAction implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();

	/**
	 * 用户登陆动作
	 * 
	 * @return
	 */
	public String login() {
		UserDAO userDAO = new UserDAOImpl();
		if (userDAO.userLogin(user)) {
			// 在session中保存登陆成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		} else {
			return "login_failure";
		}
	}

	/**
	 * 用户注销方法
	 * 
	 * @return
	 */
	@SkipValidation
	public String logout() {
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}

	/**
	 * 登陆表单验证
	 */
	
	public void validate() {
		// TODO Auto-generated method stub
		// super.validate();
		// 用户名不能为空
		if ((user.getUsername() == null)||"".equals(user.getUsername().trim())) {
			this.addFieldError("userNameError", "用户名不能为空！");
		}
		// 密码长度不能少于6位
		if (user.getPassword().length() < 6) {
			this.addFieldError("passwordError", "密码长度不少于6为");
		}
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
