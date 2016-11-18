package io.nzo.booth.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.neuland.jade4j.lexer.token.Comment;
import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;
import io.nzo.booth.common.Paging;
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
	
	
//	@ResponseBody
//	@RequestMapping(path = "/list", produces = "text/html")
//	public String list(Model model) 
	//, 
		//	@RequestParam(value = "id", required = true) String id,
			//@RequestParam(value = "page", required = false, defaultValue = "1") int page)
//	{
//		Board board = boardService.getBoard(id);
		
//		ModelMap map = boardService.findAllWithPaging(board.getBoardId(), page, 15);
//		
//		// Paging.pagination(postsTotalCount.longValue(), page, size)
//		
//		System.out.println(board.getId());
//		model.addAttribute("board", board);
//		model.addAttribute("posts", map.get("posts"));
//		model.addAttribute("paging", map.get("paging"));
		
//		JadeTemplate template = JadeConfig.getTemplate("list");
//		String html = JadeConfig.renderTemplate(template, model.asMap());
//		return html;
//	}
	
//	@ResponseBody
//	@RequestMapping(path = "/view", produces = "text/html")
//	public String view(Model model, 
//			@RequestParam(value = "id", required = true) String id,
//			@RequestParam(value = "post_id", required = true) long postId)
//	{
//		JadeTemplate template = JadeConfig.getTemplate("view");
//		String html = JadeConfig.renderTemplate(template, model.asMap());
//		return html;
//	}
	
	
	@ResponseBody
	@RequestMapping(path = "/api/list", produces = "application/json")
	public String listWithApi(Model model, 
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		
		model.addAttribute("posts", boardService.getPosts(board.getTableNumber(), page, 15));
		
		Long totalCount = boardService.getPostsTotalCount(board.getTableNumber());
		model.addAttribute("paging", Paging.pagination(totalCount.longValue(), page, 15));
		
		return new JSONObject(model.asMap()).toString();
	}
	
	@ResponseBody
	@RequestMapping(path = "/api/view", produces = "application/json")
	public String listApi(Model model, 
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "post_id", required = true) long postId)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		
		Post post = boardService.getPost(board.getTableNumber(), postId);
		
		model.addAttribute("post", post);
		
		List<Comment> comments = boardService.getComments(board.getTableNumber(), postId);
		model.addAttribute("comments", comments);
		
		// user
		model.addAttribute("user", userService.getUser(post.getUserId()));
		return new JSONObject(model.asMap()).toString();
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


