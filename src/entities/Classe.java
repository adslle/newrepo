package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Classe { 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name; 
	@OneToMany(mappedBy="classe")
	private List<Section> sections;  
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return name; 
	}
	public String getName() {
		return name;
	}
	public Classe(String name) {
		super();
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
