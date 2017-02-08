package action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import service.StudentDAO;
import service.impl.StudentDAOImpl;

/**
 * 学生Action类
 * 
 * @author ASUS
 *
 */
public class StudentAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询所有学生的动作
	 * 
	 * @return
	 */
	public String query() {
		StudentDAO studentDAO = new StudentDAOImpl();
		List<Student> list = studentDAO.queryAllStudents();
		// 放进session中
		if (list != null) {
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}
	
	/**
	 *根据sid查询特定学生
	 * 
	 * @return
	 */
	public String queryStudentBySid() {
		StudentDAO studentDAO = new StudentDAOImpl();
		String sid = request.getParameter("sid");
		Student student = studentDAO.queryStudentBySid(sid);
		if (student != null) {
			List<Student> list = new ArrayList<Student>();
			list.add(student);
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}

	/**
	 * 删除学生的动作
	 */
	public String delete() {
		StudentDAO studentDAO = new StudentDAOImpl();
		String sid = request.getParameter("sid");
		studentDAO.deleteStudent(sid);
		return "delete_success";
	}

	/**
	 * 添加学生的动作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Student student = new Student();
		student.setSname(request.getParameter("sname"));
		student.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		student.setBirthday(sdf.parse(request.getParameter("birthday")));
		student.setAddress(request.getParameter("address"));
		StudentDAO studentDAO = new StudentDAOImpl();
		studentDAO.addStudent(student);
		return "add_success";
	}

	/**
	 * 修改学生资料的动作
	 * 
	 * @return
	 */
	public String modify() {
		// 获得传递过来的学生编号
		String sid = request.getParameter("sid");
		StudentDAO studentDAO = new StudentDAOImpl();
		Student student = studentDAO.queryStudentBySid(sid);
		// 保存在会话中
		session.setAttribute("modify_student", student);
		return "modify_success";
	}
	
	/**
	 * 保存修改后的学生资料的动作
	 * @return
	 * @throws Exception 
	 */
	public String save() throws Exception {
		Student student = new Student();
		student.setSid(request.getParameter("sid"));
		student.setSname(request.getParameter("sname"));
		student.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		student.setBirthday(sdf.parse(request.getParameter("birthday")));
		student.setAddress(request.getParameter("address"));
		StudentDAO studentDAO = new StudentDAOImpl();
		studentDAO.updateStudent(student);
		return "save_success";
	}
}
