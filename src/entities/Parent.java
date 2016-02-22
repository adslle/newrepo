package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Parent { 
	@Override
	public String toString() {
		return name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String num;  
	private String profession;
	@OneToMany(mappedBy="parent")
	private List<Student> students;

	public List<Student> getStudents() { 
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students; 
	}
	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Parent(String name, String email, String num, String profession) {
		super();
		this.name = name;
		this.email = email;
		this.num = num;
		this.profession = profession; 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
}
