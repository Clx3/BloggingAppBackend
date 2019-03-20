package fi.tuni.bloggingapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blogPost")
public class BlogPost {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="postDate")
	private LocalDate date;
	
	@Column(name="author")
	private String author;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	public BlogPost() {}
	
	public BlogPost(LocalDate date, String author, String title, String content) {
		this.date = date;
		this.author = author;
		this.title = title;
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
