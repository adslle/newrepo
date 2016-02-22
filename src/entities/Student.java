package entities;

import java.io.File;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	private Parent parent; 
	@ManyToOne
	private Section section; 
	@Override
	public String toString() { 
		return "Student [id=" + id + ", name=" + name + ", dateBith=" + dateBith + "]";
	}

	private Date dateBith;
	private File image; 
	public Student() {
		super();
		// TODO Auto-generated constructor stub
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
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Date getDateBith() {
		return dateBith;
	}
	public void setDateBith(Date dateBith) {
		this.dateBith = dateBith;
	}

	public Student(String name, Date dateBith, File image) {
		super();
		this.name = name;
		this.dateBith = dateBith;
		this.image = image;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}


}
