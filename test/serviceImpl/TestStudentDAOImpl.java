package serviceImpl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entity.Student;
import service.StudentDAO;
import service.impl.StudentDAOImpl;

public class TestStudentDAOImpl {

	@Test
	public void testQueryAllStudents() {
		StudentDAO studentDAO = new StudentDAOImpl();
		List<Student> list = studentDAO.queryAllStudents();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testGetNewSid() {
		StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
		// System.out.println(studentDAOImpl.getNewSid());
	}

	@Test
	public void testAddStudent() {
		Student student = new Student();
		student.setSname("ÕÅÎ°");
		student.setGender("ÄÐ");
		student.setBirthday(new Date());
		student.setAddress("¶ëÃ¼É½");
		StudentDAO studentDAO = new StudentDAOImpl();
		Assert.assertEquals(true, studentDAO.addStudent(student));
	}
}
