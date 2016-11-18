package io.nzo.booth.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import io.nzo.orm.HibernateUtil;

// Board, Post, Comment


@Component
public class BoardService
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// get board
	public Board getBoard(String id) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Board where id = :id")
				.setParameter("id", id);
		query.setFetchSize(1);
		query.setMaxResults(1);
		
		return (Board)query.getSingleResult();
	}
	
	public Board getBoard(int boardId) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return 	(Board) session.get(Board.class, boardId);
	}
	
	// get posts
	@SuppressWarnings("unchecked")
	public List<Post> getPosts(int tableNumber, int page, int size)
	{
		if (page <= 0) { page = 1; }
		if (size <= 0) { size = 15; }

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		@SuppressWarnings({ "rawtypes" })
		Query query = session.createQuery("from Post" + String.valueOf(tableNumber));
		query.setFirstResult( ((page - 1) * size) );
		query.setFetchSize(size);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	public Post getPost(int tableNumber, long postId)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Post) session.get("Post" + String.valueOf(tableNumber), postId);
	}
	
	
	
	public Long getPostsTotalCount(int tableNumber) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Long) session
				.createQuery("select count(p.postId) from Post" + String.valueOf(tableNumber) + " p")
				.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(int boardTableNumber, long postId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		return session.createQuery("from Comment"+ String.valueOf(boardTableNumber)+" where postId = :postId")
			.setParameter("postId", postId)
			.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 글 작성
	public ModelMap addPost()
	{
		
		return null;
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