package io.nzo.booth.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import de.neuland.jade4j.lexer.token.Comment;
import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;
import io.nzo.booth.common.Paging;
import io.nzo.booth.model.Board;
import io.nzo.booth.model.Post;
import io.nzo.booth.service.BoardService;
import io.nzo.booth.service.UserService;

@Controller
@SessionAttributes("user")
public class BoardController
{
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	String url = "http://127.0.0.1:8080";
	String app = "booth";
	
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	BoardService boardService;
	
	
	
//	@ResponseBody
//	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML)
//	public String board(Model model, @PathVariable("id") String id)
//	{
//		model.addAttribute("url", url);
//		model.addAttribute("app", app);
//		model.addAttribute("id", id);
//		JadeTemplate template = JadeConfig.getTemplate("board");
//		return JadeConfig.renderTemplate(template, model.asMap());
//	}
//	
//	@ResponseBody
//	@RequestMapping(path = "/{id}/list", produces = MediaType.TEXT_HTML)
//	public String list(Model model,
//			@PathVariable("id") String id,
//			@RequestParam(value = "spf", required = false ) String spf) 
//	{
//		String head = JadeConfig.renderTemplate(JadeConfig.getTemplate("head"), model.asMap());
//		String board = JadeConfig.renderTemplate(JadeConfig.getTemplate("list"), model.asMap());	// board = id
//		if( "navigate".equals(spf) ) 
//		{
//			JSONObject json = new JSONObject();
//			json.put("title", "게시물 목록");
//			json.put("head", head);
//			json.put("body", new JSONObject().put(id, board));
//			return json.toString();
//		}
//		else
//		{
//			return "";
//		}
//	}
//	
//	@ResponseBody
//	@RequestMapping(path = "/e/view", produces = MediaType.TEXT_HTML)
//	public String view(Model model)
//	{
//		JadeTemplate template = JadeConfig.getTemplate("view");
//		return JadeConfig.renderTemplate(template, model.asMap());
//	}
//	
//	@ResponseBody
//	@RequestMapping(path = "/e/write", produces = MediaType.TEXT_HTML)
//	public String write(Model model)
//	{
//		JadeTemplate template = JadeConfig.getTemplate("write");
//		return JadeConfig.renderTemplate(template, model.asMap());
//	}
	
	
	
	
//	// add / modify
	@ResponseBody
	@RequestMapping(path = "/gz/post/add", method = RequestMethod.POST)
	public String setPostAdd(Model model, 
			@RequestBody String payload)
	{
		JSONObject params = new JSONObject(payload);
		
		boardService.postAdd(params.getString("id"), params.getString("title"), params.getString("text") );
		return new JSONObject().toString();
	}
	
	
	
	// 게시물 보기
	@ResponseBody
	@RequestMapping(path = "/gz/post", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON)
	public String listApi(Model model, 
			@RequestParam(value = "id", required = false, defaultValue="" ) String id,
			@RequestParam(value = "postId", required = true) long postId)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		
		Post post = boardService.getPost(board.getTableNumber(), postId);
		
		model.addAttribute("post", post);
		
		List<Comment> comments = boardService.getComments(board.getTableNumber(), postId);
		model.addAttribute("postComments", comments);
		
		// user
		model.addAttribute("postUser", userService.getUser(post.getUserId()));
		
		model.addAttribute("nextPost", boardService.getNextPost(board.getTableNumber(), postId));
		model.addAttribute("prevPost", boardService.getPrevPost(board.getTableNumber(), postId));
		
		return new JSONObject(model.asMap()).toString();
	}
	
	
	
	
	

	@ResponseBody
	@RequestMapping(path = "/gz/list", produces = MediaType.APPLICATION_JSON)
	public String listWithApi(Model model, 
			@RequestParam(value = "id", required = false, defaultValue="" ) String id,
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
	@RequestMapping(path = "/gz/board", produces = MediaType.APPLICATION_JSON)
	public String listApi(Model model, 
			@RequestParam(value = "id", required = false, defaultValue="") String id)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		
		return new JSONObject(model.asMap()).toString();
	}
	
	
	
	
	
//	
//	
//	// remove
//	@ResponseBody
//	@RequestMapping(path = "/gz/post", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON )
//	public String setPostRemove(Model model,
//			HttpSession session,
//			@RequestParam(value = "id", required = true ) String id,
//			@RequestParam(value = "title", required = false) String title,
//			@RequestBody(required = false) String text)
//	{
//		if( title.length() > 250 ) {
//			title = title.substring(0, 250);
//		}
//		// TODO 이부분 해결안됨
//		
//		//User user = (User) session.getAttribute("user");
//		boardService.postAdd(id, 0L, 1, false, false, title, text, "", "", "");
//		return new JSONObject().toString();
//	}
//	
//	
//
//	// get
//	@ResponseBody
//	@RequestMapping(path = "/gz/post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
//	public String getPost(Model model,
//			HttpSession session,
//			@RequestParam(value = "id", required = true ) String id,
//			@RequestParam(value = "title", required = false) String title,
//			@RequestBody(required = false) String text)
//	{
//		if( title.length() > 250 ) {
//			title = title.substring(0, 250);
//		}
//		// TODO 이부분 해결안됨
//		
//		//User user = (User) session.getAttribute("user");
//		boardService.postAdd(id, 0L, 1, false, false, title, text, "", "", "");
//		return new JSONObject().toString();
//	}
	
	
	
	
	
	
	
	
}