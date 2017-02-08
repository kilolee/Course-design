package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.User;
import service.UserDAO;
import service.impl.UserDAOImpl;

/**
 * �û�Action��
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
	 * �û���½����
	 * 
	 * @return
	 */
	public String login() {
		UserDAO userDAO = new UserDAOImpl();
		if (userDAO.userLogin(user)) {
			// ��session�б����½�ɹ����û���
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		} else {
			return "login_failure";
		}
	}

	/**
	 * �û�ע������
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
	 * ��½����֤
	 */
	
	public void validate() {
		// TODO Auto-generated method stub
		// super.validate();
		// �û�������Ϊ��
		if ((user.getUsername() == null)||"".equals(user.getUsername().trim())) {
			this.addFieldError("userNameError", "�û�������Ϊ�գ�");
		}
		// ���볤�Ȳ�������6λ
		if (user.getPassword().length() < 6) {
			this.addFieldError("passwordError", "���볤�Ȳ�����6Ϊ");
		}
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
