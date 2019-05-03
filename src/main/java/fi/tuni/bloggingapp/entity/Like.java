package fi.tuni.bloggingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blog_like")
public class Like {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "blogpostId")
	private long blogpostId;

	public Like() {}
	
	public Like(long blogpostId) {
		this.blogpostId = blogpostId;
	}

	public long getBlogpostId() {
		return blogpostId;
	}

	public void setBlogpostId(long blogpostId) {
		this.blogpostId = blogpostId;
	}

}
