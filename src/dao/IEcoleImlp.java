package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Classe;
import entities.Parent;
import entities.Section;
import entities.Student;
import entities.Teacher;

public class IEcoleImlp implements IEcole {
	EntityManagerFactory emf;
	private EntityManager em; 
	EntityTransaction t ; 
	public void close(){

		em.close();
		emf.close();
	}
	public IEcoleImlp() {
		
		emf=Persistence.createEntityManagerFactory("MyPU");
		em = emf.createEntityManager();
		t = em.getTransaction(); 
		
	}
	


	@Override
	public ArrayList<Classe> getClasses() {
		Query req=em.createQuery("select c from Classe c");
		return (ArrayList<Classe>) req.getResultList();

	}

	@Override
	public ArrayList<Parent> getParents() {

		Query req=em.createQuery("select p from Parent p");
		return (ArrayList<Parent>) req.getResultList(); 
	}


	@Override 
	public Student updateOrAddStudent(Student student, int parentId, int sectionId){ 
		t.begin(); 
		Parent p = em.find(Parent.class, parentId);student.setParent(p);
		Section sec = em.find(Section.class, sectionId);student.setSection(sec);  
		if(student.getId()!=0){

			System.out.println("update "+student);
			em.merge(student);
		}
		else{
			System.out.println("add "+student);
			em.persist(student);   

		}
		t.commit();
		return student; 
	}
	@Override
	public boolean removeStudent(int id) {
		try {
			t.begin();
			Student s = em.find(Student.class, id);
			em.remove(s);;
			t.commit();
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public Student getStudent(int id) {
		return em.find(Student.class, id);
	}
	@Override
	public Classe updateOrAddClasse(Classe classe) {
		t.begin(); 
		em.persist(classe);   
		t.commit();
		return classe; 
	}
	@Override
	public Section updateOrAddSection(Section section, int classeId, int teacherId) {
		t.begin(); 
		Teacher teacher = em.find(Teacher.class, teacherId);section.setTeacher(teacher);
		Classe classe = em.find(Classe.class, classeId);section.setClasse(classe);
		if(section.getId()!=0)
			em.merge(section);
		else
			em.persist(section);   
		t.commit();
		return section; 
	}
	@Override
	public Teacher updateOrAddTeacher(Teacher teacher) {
		t.begin(); 
		if(teacher.getId()!=0)
		em.merge(teacher);   
		else
		em.persist(teacher); 
		t.commit();
		return teacher; 
	}
	@Override
	public ArrayList<Teacher> getTeachers() {
		Query req=em.createQuery("select t from Teacher t");
		return (ArrayList<Teacher>) req.getResultList();

	}
	@Override
	public boolean removeTeacher(int id) {
		try {
			t.begin();
			Teacher te = em.find(Teacher.class, id); 
			em.remove(te);;
			t.commit();
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean removeClasse(int id) {
		try {
			t.begin();
			Classe cl = em.find(Classe.class, id);
			em.remove(cl);;
			t.commit();
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean removeSection(int id) {
		try {
			t.begin();
			Section s = em.find(Section.class, id);
			em.remove(s);
			t.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public Teacher getTeacher(int id) {
		return em.find(Teacher.class, id); 
	}
	@Override
	public ArrayList<Section> getSections() {

		Query req=em.createQuery("select s from Section s");
		return (ArrayList<Section>) req.getResultList();  
	}
	@Override
	public List<Section> getSectionsByClasse(int idClasse) {

		Query req=em.createQuery("select s from Section s where s.classe.id=:x");
		req.setParameter("x", idClasse); 
		return req.getResultList();  
	}
	@Override
	public List<Student> getStudentsBySection(int idSection) {
		Query req=em.createQuery("select s from Student s where s.section.id=:x");   
		req.setParameter("x", idSection); 
		return req.getResultList();  
	}
	

}
