package io.nzo.booth.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long userId;
	
	private String username;
	private String passwordSha2;
	private String name;
	private String email;
	private String homepage;
	private String comment;
	private Integer point = 0;
	private Short permission = 9;
	private Boolean admin = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPasswordSha2()
	{
		return passwordSha2;
	}

	public void setPasswordSha2(String passwordSha2)
	{
		this.passwordSha2 = passwordSha2;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public Integer getPoint()
	{
		return point;
	}

	public void setPoint(Integer point)
	{
		this.point = point;
	}

	public Short getPermission()
	{
		return permission;
	}

	public void setPermission(Short permission)
	{
		this.permission = permission;
	}
	
	public Boolean getAdmin()
	{
		return admin;
	}

	public void setAdmin(Boolean admin)
	{
		this.admin = admin;
	}

	public Date getCreationTime()
	{
		return creationTime;
	}

	public void setCreationTime(Date creationTime)
	{
		this.creationTime = creationTime;
	}

}