package application.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class Photos {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int Id;
	@Column(name="title")
	private String Title;
	@Column(name="image")
	private Blob data;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Blob getData() {
		return data;
	}
	public void setData(Blob data) {
		this.data = data;
	}
	
	
}
