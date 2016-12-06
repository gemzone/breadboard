package io.nzo.booth.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import io.nzo.booth.common.Paging;
import io.nzo.booth.controller.UserController;
import io.nzo.booth.model.Board;
import io.nzo.booth.model.Comment;
import io.nzo.booth.model.Post;
import io.nzo.orm.HibernateUtil;

// Board, Post, Comment

@Component
public class BoardService
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// get board
	public Board getBoard(String id) 
	{
		Board board = null;
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from Board where id = :id")
					.setParameter("id", id);
			query.setFetchSize(1);
			query.setMaxResults(1);
			
			board = (Board)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			logger.debug(e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return board;
	}
	
	public Board getBoard(int boardId) 
	{
		Board board = null;
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			board = (Board) session.get(Board.class, boardId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return board;
	}
	
	// get posts
	@SuppressWarnings("unchecked")
	public List<Post> getPosts(int tableNumber, int page, int size)
	{
		if (page <= 0) { page = 1; }
		if (size <= 0) { size = 15; }
		
		List<Post> posts = null;
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			@SuppressWarnings({ "rawtypes" })
			Query query = session.createQuery("from Post" + String.valueOf(tableNumber));
			query.setFirstResult( ((page - 1) * size) );
			query.setFetchSize(size);
			query.setMaxResults(size);
			posts = query.getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return posts;
	}
	
	public Post getPost(int tableNumber, long postId)
	{
		Post post = null;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			post = (Post) session.get("Post" + String.valueOf(tableNumber), postId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return post;
	}

	/**
	 * 다음게시물
	 * @param tableNumber
	 * @param postId
	 * @return
	 */
	public Post getNextPost(int tableNumber, long postId)
	{
		Post post = null;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			// TODO 여기에 orderby asc 붙이는걸 나중에 다른방법으로 해결해야함
			@SuppressWarnings({ "rawtypes" })
			Query query = session.createQuery("from Post" + String.valueOf(tableNumber) + " where postId > " + postId + " order by postId asc");
			query.setFetchSize(1);
			query.setMaxResults(1);

			post = (Post) query.getSingleResult();
		}
		catch(NoResultException e)
		{
			post = new Post();
			post.setTitle("next_post_not_found");
			return post;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return post;
	}
	
	/**
	 * 이전 게시물
	 * @param tableNumber
	 * @param postId
	 * @return
	 */
	public Post getPrevPost(int tableNumber, long postId)
	{
		Post post = null;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			@SuppressWarnings({ "rawtypes" })
			Query query = session.createQuery("from Post" + String.valueOf(tableNumber) + " where postId < " + postId );
			query.setFetchSize(1);
			query.setMaxResults(1);

			post = (Post) query.getSingleResult();
		}
		catch(NoResultException e)
		{
			post = new Post();
			post.setTitle("prev_post_not_found");
			return post;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return post;
	}
	
	public Long getPostsTotalCount(int tableNumber) 
	{
		Long count = 0L;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			count = (Long) session
					.createQuery("select count(p.postId) from Post" + String.valueOf(tableNumber) + " p")
					.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(int boardTableNumber, long postId)
	{
		List<Comment> comments = null;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			comments = session.createQuery("from Comment"+ String.valueOf(boardTableNumber)+" where postId = :postId")
					.setParameter("postId", postId)
					.getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return comments;
	}
	
	
	public void setPostIncreaseViewCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET hit_count += :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void setPostIncreaseUpCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET up_count += :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPostIncreaseDownCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET down_count += :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPostIncreaseCommentCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET comment_count += :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	public void setPostDecreaseViewCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET hit_count -= :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void setPostDecreaseUpCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET up_count -= :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPostDecreaseDownCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET down_count -= :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPostDecreaseCommentCount(Integer boardTableNumber, Long postId, int count) 
	{
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + boardTableNumber.toString() + " SET comment_count -= :count WHERE post_id = :post_id";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("count", count);
			query.setParameter("post_id", postId);
			query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	// 글 작성
	public int postAdd(String id, Post post)
	{
		int execute = 0;
		
		Board board = getBoard(id);
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "INSERT INTO gz_post" + board.getTableNumber().toString() + " (board_id, uid, user_id, category_id, notice, secret, title, text, attachment, link, ip) " +
			     " VALUES (:board_id, NEWID(), :user_id, :category_id, :notice, :secret, :title, :text, :attachment, :link, :ip) ";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("board_id", board.getBoardId());
			query.setParameter("user_id", null);
			query.setParameter("category_id", post.getCategoryId());
			query.setParameter("notice", post.getNotice());
			query.setParameter("secret", post.getSecret());
			query.setParameter("title", post.getTitle());
			query.setParameter("text", post.getText());
			query.setParameter("attachment", post.getAttachment());
			query.setParameter("link", post.getLink());
			query.setParameter("ip", post.getIp());
			execute = query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			execute = -1;
			e.printStackTrace();
		}
		return execute;
	}
	
	// 글 작성
	public int postModify(String id, Post post)
	{
		int execute = 0;
		Board board = getBoard(id);
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			String sql = "UPDATE gz_post" + board.getTableNumber().toString() 
					+" SET "
					+ "board_id=:board_id, "
					+ "user_id = :user_id, "
					+ "category_id = :category_id, "
					+ "notice = :notice, "
					+ "secret = :secret, "
					+ "title = :title, "
					+ "text = :text, "
					+ "attachment = :attachment, "
					+ "link = :link, "
					+ "ip = :ip "
					+ " WHERE post_id = :postId ";
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("board_id", board.getBoardId());
			query.setParameter("user_id", post.getUserId());
			query.setParameter("category_id", post.getCategoryId());
			query.setParameter("notice", post.getNotice());
			query.setParameter("secret", post.getSecret());
			query.setParameter("title", post.getTitle());
			query.setParameter("text", post.getText());
			query.setParameter("attachment", post.getAttachment());
			query.setParameter("link", post.getLink());
			query.setParameter("ip", post.getIp());
			
			query.setParameter("postId", post.getPostId());
			
			execute = query.executeUpdate();
			tx.commit();
		}
		catch(Exception e)
		{
			execute = -1;
			e.printStackTrace();
		}
		return execute;
	}
	
	
	// 댓글 작성
	public int commentAdd(Integer tableNumber, Comment comment)
	{
		int execute = 0;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			
			
			String sql = "INSERT INTO gz_post" + tableNumber.toString() + "_comment "
					+ " (post_id, uid, user_id, text, ip) " 
					+ " VALUES (:post_id, NEWID(), :user_id, :text, :ip)";
			
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("post_id", comment.getPostId());
			query.setParameter("user_id", comment.getUserId());
			query.setParameter("text", comment.getText());
			query.setParameter("ip", comment.getIp());
			
			execute = query.executeUpdate();
			
			tx.commit();
		}
		catch(Exception e)
		{
			execute = -1;
			e.printStackTrace();
		}
		return execute;
	}
	
	// 글 삭제
	// 코멘트 삭제
	// 첨부 파일 삭제
	public int removePost(Integer tableNumber, Post post)
	{
		int execute = 0;
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			Transaction tx = session.beginTransaction();
			
			session.delete("Post" + String.valueOf(tableNumber), post);
			
			execute = 1;
			
			tx.commit();
		}
		catch(Exception e)
		{
			execute = -1;
			e.printStackTrace();
		}
		
		return execute;
	}
	
	
	public ModelMap findAllWithPaging(int boardId, int page, int size)
	{
		ModelMap map = new ModelMap();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Board board = (Board) session.get(Board.class, boardId);
		map.addAttribute("posts", getPosts(board.getTableNumber(), page, size) );
		Long postsTotalCount = getPostsTotalCount(board.getTableNumber());
		map.addAttribute("paging", Paging.pagination(postsTotalCount.longValue(), page, size));
		return map;
	}

}