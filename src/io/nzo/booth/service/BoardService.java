package io.nzo.booth.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import io.nzo.booth.common.CustomPaging;
import io.nzo.booth.controller.UserController;
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
	
	public ModelMap findAll(int boardId, int page, int size) 
	{
		ModelMap map = new ModelMap();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        // if( boardId
        // select [store_table_id]
        
        // 전체 카운트
        Long postCount = (Long)session.createQuery("select count(p.postId) from Post0 p").getSingleResult();
        
        map.addAttribute("paging", CustomPaging.pagination(postCount.longValue(), page, size) );
        
		return map;
	}
	
}