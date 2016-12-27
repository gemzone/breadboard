package io.nzo.breadboard.model;

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
	private Integer width = 100;
	private String description = "";
	private String headerContent;
	private String footerContent;
	private String memo;
	private Long postCount;
	private Long commentCount;
	private Integer grantList;
	private Integer grantView;
	private Integer grantWrite;
	private Integer grantComment;
	private Integer grantDelete;
	private Integer grantNotice;
	private Integer grantSecret;
	
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
		if( title.length() > 250 ) {
			title = title.substring(0, 250);
		}
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
	/**
	 * @return the grantList
	 */
	public Integer getGrantList()
	{
		return grantList;
	}
	/**
	 * @param grantList the grantList to set
	 */
	public void setGrantList(Integer grantList)
	{
		this.grantList = grantList;
	}
	/**
	 * @return the grantView
	 */
	public Integer getGrantView()
	{
		return grantView;
	}
	/**
	 * @param grantView the grantView to set
	 */
	public void setGrantView(Integer grantView)
	{
		this.grantView = grantView;
	}
	/**
	 * @return the grantWrite
	 */
	public Integer getGrantWrite()
	{
		return grantWrite;
	}
	/**
	 * @param grantWrite the grantWrite to set
	 */
	public void setGrantWrite(Integer grantWrite)
	{
		this.grantWrite = grantWrite;
	}
	/**
	 * @return the grantComment
	 */
	public Integer getGrantComment()
	{
		return grantComment;
	}
	/**
	 * @param grantComment the grantComment to set
	 */
	public void setGrantComment(Integer grantComment)
	{
		this.grantComment = grantComment;
	}
	/**
	 * @return the grantDelete
	 */
	public Integer getGrantDelete()
	{
		return grantDelete;
	}
	/**
	 * @param grantDelete the grantDelete to set
	 */
	public void setGrantDelete(Integer grantDelete)
	{
		this.grantDelete = grantDelete;
	}
	/**
	 * @return the grantNotice
	 */
	public Integer getGrantNotice()
	{
		return grantNotice;
	}
	/**
	 * @param grantNotice the grantNotice to set
	 */
	public void setGrantNotice(Integer grantNotice)
	{
		this.grantNotice = grantNotice;
	}
	/**
	 * @return the grantSecret
	 */
	public Integer getGrantSecret()
	{
		return grantSecret;
	}
	/**
	 * @param grantSecret the grantSecret to set
	 */
	public void setGrantSecret(Integer grantSecret)
	{
		this.grantSecret = grantSecret;
	}
	/**
	 * @return the width
	 */
	public Integer getWidth()
	{
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width)
	{
		this.width = width;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	
	
	
	
}