package fi.tuni.bloggingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import fi.tuni.bloggingapp.security.UserType;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="username", unique=true)
	@NotNull
	@NotEmpty
	private String username;
	
	
	@Column(name="password")
	@NotNull
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(name="isAdmin")
	@NotNull
	private boolean isAdmin;
	
	public User() {}
	
	public User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsAdmin(boolean admin) {
		this.isAdmin = admin;
	}

	public UserType getIsAdmin(){
		if(isAdmin){
			return UserType.admin;
		} else {
			return UserType.user;
		}
	}
	
}
