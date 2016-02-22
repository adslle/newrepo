package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import entities.Classe;
import entities.Parent;
import entities.Section;
import entities.Student;
import entities.Teacher;

public interface IEcole {
	public void close();
	public ArrayList<Classe> getClasses();
	public ArrayList<Parent> getParents();
	public ArrayList<Teacher> getTeachers();
	public ArrayList<Section> getSections(); 
	public List<Section> getSectionsByClasse(int idClasse); 
	public Student updateOrAddStudent(Student student, int parentId, int sectionId);
	
	public Classe  updateOrAddClasse(Classe classe); 
	
	public Section updateOrAddSection(Section section, int classeId,int teacherId);
	
	public Teacher updateOrAddTeacher(Teacher teacher);   

	public Student getStudent(int id);
	public List<Student>   getStudentsBySection(int idSection);
	public Teacher getTeacher(int id); 
	public boolean removeStudent(int id);
	public boolean removeTeacher(int id);
	public boolean removeClasse(int id);
	public boolean removeSection(int id); 
		
	

}
