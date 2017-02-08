package action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import service.StudentDAO;
import service.impl.StudentDAOImpl;

/**
 * ѧ��Action��
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
	 * ��ѯ����ѧ���Ķ���
	 * 
	 * @return
	 */
	public String query() {
		StudentDAO studentDAO = new StudentDAOImpl();
		List<Student> list = studentDAO.queryAllStudents();
		// �Ž�session��
		if (list != null) {
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}
	
	/**
	 *����sid��ѯ�ض�ѧ��
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
	 * ɾ��ѧ���Ķ���
	 */
	public String delete() {
		StudentDAO studentDAO = new StudentDAOImpl();
		String sid = request.getParameter("sid");
		studentDAO.deleteStudent(sid);
		return "delete_success";
	}

	/**
	 * ���ѧ���Ķ���
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
	 * �޸�ѧ�����ϵĶ���
	 * 
	 * @return
	 */
	public String modify() {
		// ��ô��ݹ�����ѧ�����
		String sid = request.getParameter("sid");
		StudentDAO studentDAO = new StudentDAOImpl();
		Student student = studentDAO.queryStudentBySid(sid);
		// �����ڻỰ��
		session.setAttribute("modify_student", student);
		return "modify_success";
	}
	
	/**
	 * �����޸ĺ��ѧ�����ϵĶ���
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
