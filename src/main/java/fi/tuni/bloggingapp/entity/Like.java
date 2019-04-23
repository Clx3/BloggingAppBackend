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
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "blogpostId")
	private long blogpostId;

	public Like(long userId, long blogpostId) {
		this.userId = userId;
		this.blogpostId = blogpostId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBlogpostId() {
		return blogpostId;
	}

	public void setBlogpostId(long blogpostId) {
		this.blogpostId = blogpostId;
	}

}
