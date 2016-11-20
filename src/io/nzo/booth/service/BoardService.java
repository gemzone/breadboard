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

import de.neuland.jade4j.lexer.token.Comment;
import io.nzo.booth.common.Paging;
import io.nzo.booth.controller.UserController;
import io.nzo.booth.model.Board;
import io.nzo.booth.model.Post;
import io.nzo.booth.model.User;
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
	
	
	// 글 작성
	public boolean postAdd(String id, Long userId, int categoryId, boolean notice, boolean secret, String title, String text, String attachment, String link, String ip)
	{

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
			query.setParameter("category_id", categoryId);
			query.setParameter("notice", notice);
			query.setParameter("secret", secret);
			query.setParameter("title", title);
			query.setParameter("text", text);
			query.setParameter("attachment", attachment);
			query.setParameter("link", link);
			query.setParameter("ip", ip);
			
			System.out.println("hibernate executeUpdate: " + query.executeUpdate() );
			
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	// 글 삭제
	// 코멘트 삭제
	// 첨부 파일 삭제
	public boolean removePost()
	{
		
		return false;
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