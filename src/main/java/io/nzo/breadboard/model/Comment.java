package io.nzo.breadboard.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long commentId;
	private Long refCommentId = null;
	private Long postId;
	private String uid;
	

	@JoinColumn( name = "user_id" )
	private User user = null;
	
	private Long userId;
	private String passwordSha2 = null;
	private String email = null;
	private String text;
	private String ip;
	
	private Integer goodCount = 0;
	private Integer upCount = 0;
	private Integer downCount = 0;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	public Long getCommentId()
	{
		return commentId;
	}

	public void setCommentId(Long commentId)
	{
		this.commentId = commentId;
	}

	public Long getPostId()
	{
		return postId;
	}

	public void setPostId(Long postId)
	{
		this.postId = postId;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Integer getUpCount()
	{
		return upCount;
	}

	public void setUpCount(Integer upCount)
	{
		this.upCount = upCount;
	}

	public Integer getDownCount()
	{
		return downCount;
	}

	public void setDownCount(Integer downCount)
	{
		this.downCount = downCount;
	}

	public Date getModificationTime()
	{
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime)
	{
		this.modificationTime = modificationTime;
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
	 * @return the refCommentId
	 */
	public Long getRefCommentId()
	{
		return refCommentId;
	}

	/**
	 * @param refCommentId the refCommentId to set
	 */
	public void setRefCommentId(Long refCommentId)
	{
		this.refCommentId = refCommentId;
	}

	/**
	 * @return the passwordSha2
	 */
	public String getPasswordSha2()
	{
		return passwordSha2;
	}

	/**
	 * @param passwordSha2 the passwordSha2 to set
	 */
	public void setPasswordSha2(String passwordSha2)
	{
		this.passwordSha2 = passwordSha2;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the goodCount
	 */
	public Integer getGoodCount()
	{
		return goodCount;
	}

	/**
	 * @param goodCount the goodCount to set
	 */
	public void setGoodCount(Integer goodCount)
	{
		this.goodCount = goodCount;
	}
	
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
	
}