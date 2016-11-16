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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.neuland.jade4j.lexer.token.Comment;
import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;
import io.nzo.booth.model.Board;
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
	public String list(Model model, 
			@RequestParam(value = "board_id", required = true) int boardId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page)
	{
		ModelMap map = boardService.findAllWithPaging(boardId, page, 15);
		
		model.addAttribute("posts", map.get("posts"));
		model.addAttribute("paging", map.get("paging"));

		JadeTemplate template = JadeConfig.getTemplate("list");
		String html = JadeConfig.renderTemplate(template, model.asMap());
		return html;
	}
	
//	@ResponseBody
//	@RequestMapping(path = "/api/list", produces = "application/json")
//	public String listApi(Model model, 
//			@RequestParam(value = "board_id", required = true) int boardId,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page)
//	{
//		ModelMap map = boardService.findAllWithPaging(boardId, page, 15);
//		
//		model.addAttribute("posts", map.get("posts"));
//		model.addAttribute("paging", map.get("paging"));
//		
//		return new JSONObject(model.asMap()).toString();
//	}
	
	
	@ResponseBody
	@RequestMapping(path = "/view", produces = "text/html")
	public String view(Model model, 
			@RequestParam(value = "board_id", required = true) int boardId,
			@RequestParam(value = "post_id", required = true) long postId)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		boardId = 1;
		
		Board board = (Board)session.get(Board.class, boardId);
		
		Post post = (Post) session.get("Post" + board.getTableNumber().toString(), postId);
		model.addAttribute("post", post);
		
		
		// comment list
		List<Comment> comments = boardService.commentList(board.getTableNumber(), post.getPostId());
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


