package fi.tuni.bloggingapp.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity used by BlogPostRepository, that is saved to database.
 *
 * @author Teemu Salminen & Joonas Salojarvi
 * @version 2019.04.22
 * @since 0.1
 */
@Entity
@Table(name = "blogPost")
public class BlogPost {

	/**
	 * Automatically generated id
	 */
	@Id
	@GeneratedValue
	private long id;

	/**
	 * Date and time, when the post was added
	 */
	@Column(name="postDate")
	private LocalDateTime date;

	/**
	 * Author of blog post
	 */
	@Column(name="author")
	private String author;

	/**
	 * Title of blog post
	 */
	@Column(name="title")
	private String title;

	/**
	 * Content of blog post
	 */
	@Column(name="content")
	private String content;
	
	public BlogPost() {}
	
	public BlogPost(String author, String title, String content) {
		this.date = LocalDateTime.now();
		this.author = author;
		this.title = title;
		this.content = content;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getId(){
		return this.id;
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
