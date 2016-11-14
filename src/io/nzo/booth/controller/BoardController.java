package io.nzo.booth.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.neuland.jade4j.lexer.token.Comment;
import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;
import io.nzo.booth.model.Post;
import io.nzo.booth.model.User;
import io.nzo.booth.service.BoardService;
import io.nzo.booth.service.UserService;
import io.nzo.orm.HibernateUtil;

@Controller
@SessionAttributes("user")
public class BoardController
{
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	BoardService boardService;
	
	@ResponseBody
	@RequestMapping(path = "/list", produces = "text/html")
	public String list(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// comment list
		@SuppressWarnings("unchecked")
		List<Post> posts = session.createQuery("from Post0 where boardId = :boardId")
				.setParameter("boardId", 1)
				.getResultList();
		model.addAttribute("posts", posts);

		model.addAttribute("paging", boardService.findAll(1, page, 10).get("paging"));

		logger.debug(model.asMap().toString());

		JadeTemplate template = JadeConfig.getTemplate("list");
		String html = JadeConfig.renderTemplate(template, model.asMap());
		return html;
	}
	
//	@ResponseBody
//	@RequestMapping(path="/list/{page}", produces="text/html")
//	public String pageList(Model model, 
//			@PathVariable("page") int page)
//	{
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        
//    	// comment list
//    	@SuppressWarnings("unchecked")
//		List<Post> posts = session.createQuery("from Post0 where boardId = :boardId")
//    		.setParameter("boardId", 1)
//    		.getResultList();
//    	model.addAttribute("posts", posts);
//    	
//        model.addAttribute("paging",  boardService.findAll(1, page, 10).get("paging")  );
//        
//		JadeTemplate template = JadeConfig.getTemplate("list");
//		String html = JadeConfig.renderTemplate(template, model.asMap());
//		return html;
//		//return JSONObject.valueToString(model); // coArtMapJpaRepository.findAll(pageRequest);
//	}
	
//	@ResponseBody
//	@RequestMapping(path = "/list/{page}/{size}", produces = "application/json")
//	public String list(Model model, 
//			@PathVariable("page") int page,
//			@PathVariable("size") int size)
//	{
//		// Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "rowid"));
//		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        
//        model.addAttribute("paging",  boardService.findAll(1, page, 10).get("paging")  );
//        
//        System.out.println(String.valueOf(page));
//        System.out.println(String.valueOf(size));
//        
//        
//        
//		
//		return JSONObject.valueToString(model); // coArtMapJpaRepository.findAll(pageRequest);
//	}
	
	
	@ResponseBody
	@RequestMapping(path = "/view", produces = "text/html")
	public String view(Model model)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Post post = (Post) session.get("Post0", 1L);
		model.addAttribute("post", post);

		// comment list
		@SuppressWarnings("unchecked")
		List<Comment> comments = session.createQuery("from Comment0 where postId = :postId")
				.setParameter("postId", post.getPostId()).getResultList();
		model.addAttribute("comments", comments);

		// user
		User user = (User) session.get(User.class, post.getUserId());
		model.addAttribute("user", user);

		JadeTemplate template = JadeConfig.getTemplate("view");
		String html = JadeConfig.renderTemplate(template, model.asMap());
		return html;
	}
}


//SELECT COUNT(p.id) AS col_0_0_
//FROM   Person p

//assertTrue(((Number) entityManager
//	    .createQuery("select count(id) from Person")
//	    .getSingleResult()).intValue() == 0);



// Query q = s.createFilter( collection, "" ); // the trivial filter
// q.setMaxResults(PAGE_SIZE);
// q.setFirstResult(PAGE_SIZE * pageNumber);
// List page = q.list();


