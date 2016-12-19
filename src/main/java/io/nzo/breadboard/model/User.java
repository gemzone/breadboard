package io.nzo.breadboard.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long userId;
	
	private String username;
	private String passwordSha2;
	
	@NotNull
    @Size(min=2, max=30)
	private String name;
	
	private String email;
	private String homepage;
	private String comment;
	private Integer point1 = 0;
	private Integer point2 = 0;
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

	/**
	 * @return the point1
	 */
	public Integer getPoint1()
	{
		return point1;
	}

	/**
	 * @param point1 the point1 to set
	 */
	public void setPoint1(Integer point1)
	{
		this.point1 = point1;
	}

	/**
	 * @return the point2
	 */
	public Integer getPoint2()
	{
		return point2;
	}

	/**
	 * @param point2 the point2 to set
	 */
	public void setPoint2(Integer point2)
	{
		this.point2 = point2;
	}

}