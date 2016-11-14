package io.nzo.booth.service;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import io.nzo.booth.controller.UserController;
import io.nzo.booth.model.User;
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
		
		User user = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        // if( boardId
        // select [store_table_id]
        
        // 전체 카운트
        Long postCount = (Long)session.createQuery("select count(p.postId) from Post0 p").getSingleResult();
        
		long totalCount = postCount.longValue();	// 전체 행 카운트
		long listCount = size;			// 한페이지에 보여지는 행개수 (목록에 보여지는 글제목 수)
		long pageCount = 9;			// 고정값 (하단 가로로 보여지는 페이지수)
		long currentPage = page;		// 현재 보는 몇 페이지인지
		long totalPageCount = 0;		// calc
		long startPage = 0;			// calc
		long endPage = 0;				// calc
		long prevPage = 0;				// calc
		long nextPage = 0;				// calc
		
		totalPageCount = ( totalCount % listCount != 0) ? totalCount / listCount + 1 : totalCount / listCount;
		startPage = ( currentPage % pageCount != 0) ? ( currentPage / pageCount ) * pageCount + 1 : ( currentPage / pageCount - 1 ) * pageCount + 1;
		endPage = (startPage + pageCount - 1 > totalPageCount) ? totalPageCount : startPage + pageCount - 1;
		prevPage = (currentPage <= 1) ? 1 :  currentPage - 1;
		nextPage = (currentPage >= totalPageCount) ? totalPageCount : currentPage + 1;
		
		
		
		
//		xsw.writeStartElement("total_count");
//		xsw.writeCharacters( String.valueOf(totalCount) );
//		xsw.writeEndElement();
//		xsw.writeStartElement("list_count");
//		xsw.writeCharacters( String.valueOf(listCount) );
//		xsw.writeEndElement();
//		xsw.writeStartElement("page_count");
//		xsw.writeCharacters( String.valueOf(pageCount) );
//		xsw.writeEndElement();
//		xsw.writeStartElement("current_page");
//		xsw.writeCharacters( String.valueOf(currentPage) );
//		xsw.writeEndElement();
//		xsw.writeStartElement("total_page_count");
//		xsw.writeCharacters( String.valueOf(totalPageCount) );
//		xsw.writeEndElement();
//		xsw.writeStartElement("prev_page");
//		xsw.writeCharacters( String.valueOf(prevPage) );
//		xsw.writeEndElement();
//		
//		for( long i = startPage; i < endPage + 1; i++ )
//		{
//			xsw.writeStartElement("page");
//			if( currentPage == i )
//			{
//				xsw.writeAttribute("active", "1");
//			}
//			xsw.writeCharacters( String.valueOf(i) );
//			xsw.writeEndElement();
//		}
//		
//		xsw.writeStartElement("next_page");
//		xsw.writeCharacters( String.valueOf(nextPage) );
//		xsw.writeEndElement();
//		xsw.writeEndElement();	// end Paging
//        
//        
        
        
        
        
        
        
        
        
        
        
        
        
        
        try
		{
	        user = (User)session.get(User.class, 604L);
	        map.addAttribute("user", user);
	        map.addAttribute("success", true);
		} 
		catch (NoResultException e)
		{
			map.addAttribute("reason", e.getMessage());
			map.addAttribute("error", 1);
			map.addAttribute("success", false);
		}
		return map;
	}
	
}