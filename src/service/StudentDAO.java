package service;

import java.util.List;

import entity.Student;

/**
 * ѧ��ҵ���߼��ӿ�
 * 
 * @author ASUS
 *
 */
public interface StudentDAO {
	// ��ѯ����ѧ������
	public List<Student> queryAllStudents();

	// ����ѧ����Ų�ѯѧ������
	public Student queryStudentBySid(String sid);

	// ���ѧ������
	public boolean addStudent(Student s);

	// �޸�ѧ������
	public boolean updateStudent(Student s);

	// ɾ��ѧ������
	public boolean deleteStudent(String sid);
}
