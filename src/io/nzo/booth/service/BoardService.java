package io.nzo.booth.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import io.nzo.booth.common.CustomPaging;
import io.nzo.booth.controller.UserController;
import io.nzo.booth.model.Board;
import io.nzo.booth.model.Post;
import io.nzo.orm.HibernateUtil;

// Board, Post, Comment


@Component
public class BoardService
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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

		if (page <= 0)
		{
			page = 1;
		}
		if (size <= 0)
		{
			size = 15;
		}

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Board board = (Board) session.get(Board.class, boardId);

		@SuppressWarnings({ "rawtypes" })
		Query query = session.createQuery("from Post" + board.getTableNumber().toString());
		query.setFirstResult( ((page - 1) * size)    );
		query.setFetchSize(size);
		query.setMaxResults(size);

		@SuppressWarnings("unchecked")
		List<Post> posts = query.getResultList();

		// List<Post> posts = new ArrayList<Post>();
		map.addAttribute("posts", posts);

		// 전체 카운트 HQL
		Long postCount = (Long) session
				.createQuery("select count(p.postId) from Post" + board.getTableNumber().toString() + " p")
				.getSingleResult();

		map.addAttribute("paging", CustomPaging.pagination(postCount.longValue(), page, size));

		return map;
	}

}