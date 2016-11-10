package io.nzo.booth.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Board implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="board_id", unique=true, nullable=false)
	Integer boardId;
	
	@Column(name="server_id")
	Integer serverId;
	
	@Column(name="key")
	String key;
	
	@Column(name="title")
	String title;
	
	@Column(name="header_content")
	String header_content;
	
	@Column(name="footer_content")
	String footer_content;
	
	@Column(name="memo")
	String memo;
	
	@Column(name="post_count")
	Long postCount;
	
	@Column(name="permission")
	Short permission;
	
	@Column(name="creation_time")
	Date creationTime;

	
	
	public Integer getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Integer boardId)
	{
		this.boardId = boardId;
	}

	public Integer getServerId()
	{
		return serverId;
	}

	public void setServerId(Integer serverId)
	{
		this.serverId = serverId;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getHeader_content()
	{
		return header_content;
	}

	public void setHeader_content(String header_content)
	{
		this.header_content = header_content;
	}

	public String getFooter_content()
	{
		return footer_content;
	}

	public void setFooter_content(String footer_content)
	{
		this.footer_content = footer_content;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public Long getPostCount()
	{
		return postCount;
	}

	public void setPostCount(Long postCount)
	{
		this.postCount = postCount;
	}

	public Short getPermission()
	{
		return permission;
	}

	public void setPermission(Short permission)
	{
		this.permission = permission;
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