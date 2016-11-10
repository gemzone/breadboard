package io.nzo.booth.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="comment_id", unique=true, nullable=false)
	Long commentId;

	@Column(name="post_id")
	Long postId;
	
	@Column(name="user_id")
	Long userId;
	
	@Column(name="text")
	String text;

	@Column(name="up_count")
	Integer upCount;
	
	@Column(name="down_count")
	Integer downCount;
	
	@Column(name="ip")
	String ip;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modification_time", nullable = true)
	Date modificationTime;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="deletion_time", nullable = true)
	Date deletionTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_time")
	Date creation_time;
	
	
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

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getModificationTime()
	{
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime)
	{
		this.modificationTime = modificationTime;
	}

	public Date getDeletionTime()
	{
		return deletionTime;
	}

	public void setDeletionTime(Date deletionTime)
	{
		this.deletionTime = deletionTime;
	}

	public Date getCreation_time()
	{
		return creation_time;
	}

	public void setCreation_time(Date creation_time)
	{
		this.creation_time = creation_time;
	}
	
	
}