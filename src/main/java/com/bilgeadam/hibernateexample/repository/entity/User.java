package com.bilgeadam.hibernateexample.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbluser")
public class User {

	@Id

//	@SequenceGenerator(name = "sq_tbluser", sequenceName = "sq_tbluser_id",initialValue = 1, allocationSize = 1)
//	@GeneratedValue(generator = "sq_tbluser_id")

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(unique = true, length = 100, nullable = false)
	private String userName;

	@Column(length = 30)
	private String password;

	public void setId(long id) {
		this.id = id;
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;

	}

	public User(long id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;

	}

	public User() {
		super();
	}

	public User(String userName, String password, UserDetail userDetail) {
		super();
		this.userName = userName;
		this.password = password;
		this.userDetail = userDetail;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tblUserxxDetailsId") // foreignkey in adını belirlemek için kullanılır.
	private UserDetail userDetail;

	@OneToMany(mappedBy = "user")
	private List<Post> post;

	public User(String userName, String password, UserDetail userDetail, List<Post> post) {
		super();
		this.userName = userName;
		this.password = password;
		this.userDetail = userDetail;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", userDetail=" + userDetail
				+ ", post=" + post + "]";
	}

}
