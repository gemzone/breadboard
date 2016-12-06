package io.nzo.booth.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
 
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Post implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long postId = 0L;
	
	private Integer boardId;
	private String uid;
	private Long userId = null;
	private Integer categoryId = 1;
	private Boolean notice = false;
	private Boolean secret = false;
	private String title = "";
	private String text = "";
	private String attachment = "";
	private String link = "";
	private String ip = "0:0:0:0:0:ffff:0:0";
	private Integer hitCount = 0;
	private Integer upCount = 0;
	private Integer downCount = 0;
	
	private Integer commentCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	public Long getPostId()
	{
		return postId;
	}

	public void setPostId(Long postId)
	{
		this.postId = postId;
	}

	public Integer getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Integer boardId)
	{
		this.boardId = boardId;
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

	public Integer getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	public Boolean getNotice()
	{
		return notice;
	}

	public void setNotice(Boolean notice)
	{
		this.notice = notice;
	}

	public Boolean getSecret()
	{
		return secret;
	}

	public void setSecret(Boolean secret)
	{
		this.secret = secret;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		if( title.length() > 250 ) {
			title = title.substring(0, 250);
		}
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

	public String getAttachment()
	{
		return attachment;
	}

	public void setAttachment(String attachment)
	{
		this.attachment = attachment;
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

	public Integer getHitCount()
	{
		return hitCount;
	}

	public void setHitCount(Integer hitCount)
	{
		this.hitCount = hitCount;
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
	
	
	public Integer getCommentCount()
	{
		return commentCount;
	}

	public void setCommentCount(Integer commentCount)
	{
		this.commentCount = commentCount;
	}
}