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
public class Board implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id         
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer boardId;
	
	private String id;
	
	private Integer tableNumber;
	private String title;
	private String headerContent;
	private String footerContent;
	private String memo;
	private Long postCount;
	private Long commentCount;
	private Integer permission;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;
	
	public Integer getBoardId()
	{
		return boardId;
	}
	public void setBoardId(Integer boardId)
	{
		this.boardId = boardId;
	}
	public Integer getTableNumber()
	{
		return tableNumber;
	}
	public void setTableNumber(Integer tableNumber)
	{
		this.tableNumber = tableNumber;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getHeaderContent()
	{
		return headerContent;
	}
	public void setHeaderContent(String headerContent)
	{
		this.headerContent = headerContent;
	}
	public String getFooterContent()
	{
		return footerContent;
	}
	public void setFooterContent(String footerContent)
	{
		this.footerContent = footerContent;
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
	public Long getCommentCount()
	{
		return commentCount;
	}
	public void setCommentCount(Long commentCount)
	{
		this.commentCount = commentCount;
	}
	public Integer getPermission()
	{
		return permission;
	}
	public void setPermission(Integer permission)
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
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	
	
	
	
}