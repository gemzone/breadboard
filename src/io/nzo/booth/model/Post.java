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
public class Post implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="post_id", unique=true, nullable=false)
	Long postId;
	
	@Column(name="user_id")
	Long userId;
	
	// @ManyToOne(cascade=CascadeType.ALL)
	// @JoinColumn(name="user_id")
	// User user;
	
	@Column(name="title")
	String title;
	
	@Column(name="text")
	String text;
	
	@Column(name="file")
	String file;
	
	@Column(name="link")
	String link;
	
	@Column(name="ip")
	String ip;
		
	@Column(name="view_count")
	Integer viewCount;
	
	@Column(name="up_count")
	Integer upCount;
	
	@Column(name="down_count")
	Integer downCount;
	
	@Column(name="is_notice")
	Boolean isNotice;
	
	@Column(name="is_private")
	Boolean isPrivate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modification_time")
	Date modificationTime;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="deletion_time")
	Date deletionTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_time")
	Date creation_time;
	
	
	public Long getPostId()
	{
		return postId;
	}

	public void setPostId(Long postId)
	{
		this.postId = postId;
	}

//	public User getUser()
//	{
//		return user;
//	}
//
//	public void setUser(User user)
//	{
//		this.user = user;
//	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getFile()
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Integer getViewCount()
	{
		return viewCount;
	}

	public void setViewCount(Integer viewCount)
	{
		this.viewCount = viewCount;
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

	public Boolean getIsNotice()
	{
		return isNotice;
	}

	public void setIsNotice(Boolean isNotice)
	{
		this.isNotice = isNotice;
	}

	public Boolean getIsPrivate()
	{
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate)
	{
		this.isPrivate = isPrivate;
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