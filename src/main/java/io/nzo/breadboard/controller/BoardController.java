package io.nzo.breadboard.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import io.nzo.breadboard.common.Paging;
import io.nzo.breadboard.model.Board;
import io.nzo.breadboard.model.Comment;
import io.nzo.breadboard.model.Post;
import io.nzo.breadboard.model.User;
import io.nzo.breadboard.service.BoardService;
import io.nzo.breadboard.service.UserService;

@Controller
@SessionAttributes("user")
public class BoardController
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired UserService userService;
	@Autowired BoardService boardService;
	
	/**
	 * 목록보기
	 */
//	@RequestMapping(path = "/board/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_HTML )
//	public String boardMain(Model model, 
//			@PathVariable( name = "id", required = true) String id,
//			@RequestParam( name="page", required = false , defaultValue = 1) int page )
//	{
//		return boardMain(model, id, page);
//	}
	@RequestMapping(path =  "/board/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_HTML )
	public String main(Model model,
			@PathVariable( name = "id", required = true) String id,
			@RequestParam( name="page", required = false , defaultValue = "1") int page,
			HttpServletRequest request)
	{
		if( page <= 0 ) { page = 1; }
		
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		
		List<Post> posts = boardService.getPosts(board.getBoardId(), board.getTableNumber(), page, 12);
		model.addAttribute("posts", posts);
		
		Long totalCount = boardService.getPostsTotalCount(board.getBoardId(), board.getTableNumber());
		model.addAttribute("paging", Paging.pagination(totalCount.longValue(), page, 12));
		
		model.addAttribute("remoteAddr", request.getRemoteAddr());
		model.addAttribute("remoteHost", request.getRemoteHost());
		
		return "board";
	}
	
	/**
	 * 게시물 보기
	 */
	@RequestMapping(path = "/board/{id}/view/{postId}", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.TEXT_HTML)
	public String postView(Model model, HttpSession session,
			@PathVariable(value = "id", required = true ) String id,
			@PathVariable(value = "postId", required = true) long postId,
			@RequestParam( name="page", required = false , defaultValue = "1") int page)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		
		// 게시물
		Post post = boardService.getPost(board.getTableNumber(), postId);
		
		model.addAttribute("post", post);
		
		// 댓글
		List<Comment> comments = boardService.getComments(board.getTableNumber(), postId);
		model.addAttribute("postComments", comments);
		
		// 이전글 다음글
		model.addAttribute("postUser", userService.getUser(post.getUserId()));
		
		Post nextPost = boardService.getNextPost(board.getBoardId(), board.getTableNumber(), postId);
		Post prevPost = boardService.getPrevPost(board.getBoardId(), board.getTableNumber(), postId);
		model.addAttribute("nextPost", nextPost);
		model.addAttribute("prevPost", prevPost);

		model.addAttribute("page" , page);		// 현재페이지
		
		Comment comment =  new Comment();
		comment.setPostId(postId);
		if( model.containsAttribute("user") ) 
		{
			User user = (User) model.asMap().get("user");
			comment.setUserId(user.getUserId());
		}
		model.addAttribute("commentForm" , comment);
		
		// 뷰 카운트 갱신
		boardService.setPostIncreaseViewCount(board.getTableNumber(), postId, 1);
	
		return "view";
	}
	
	/**
	 * 게시물 삭제
	 */
	@RequestMapping(path = "/board/{id}/delete/{postId}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView postRemove(Model model,
			@PathVariable(value = "id", required = true ) String id,
			@PathVariable(value = "postId", required = true) long postId,
			@RequestParam( name="page", required = false , defaultValue = "1") int page)
	{
		Board board = boardService.getBoard(id);
		// 게시물
		Post post = boardService.getPost(board.getTableNumber(), postId);
		
		boardService.removePost(board.getTableNumber(), post);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/board/" + id + "?page=" + page);
		return mv;
	
	}
	
	
	/**
	 * 글쓰기
	 */
	@RequestMapping(path = "/board/{id}/write", method = RequestMethod.GET, produces = MediaType.TEXT_HTML)
	public String postWrite(Model model, 
			@PathVariable(value = "id", required = true ) String id,
			@RequestParam( name="page", required = false , defaultValue = "1") int page)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		
		Post post = new Post();
		if( model.containsAttribute("user") ) 
		{
			User user = (User) model.asMap().get("user");
			post.setUserId(user.getUserId());
		}
		model.addAttribute("postForm", post);
		return "write";
	}
	
	/**
	 * 글수정
	 */
	@RequestMapping(path = "/board/{id}/write/{postId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML)
	public String postModify(Model model, 
			@PathVariable(value = "id", required = true ) String id,
			@PathVariable(value = "postId", required = true) long postId,
			@RequestParam( name="page", required = false , defaultValue = "1") int page)
	{
		Board board = boardService.getBoard(id);
		model.addAttribute("board", board);

		model.addAttribute("page", page);
		
		
		// 게시물
		Post post = boardService.getPost(board.getTableNumber(), postId);
		model.addAttribute("postForm", post);
		return "write";
	}
	
	
	/**
	 * 글쓰기 / 글수정
	 * @param model
	 * @param id
	 * @param post
	 * @return
	 */
	@RequestMapping(path = "/board/{id}/write", method = RequestMethod.POST)
	public ModelAndView postUpdate(Model model,
			@PathVariable(value = "id", required = true ) String id,
			@RequestParam( name="page", required = false , defaultValue = "1") int page,
			@ModelAttribute("postForm") Post post)
	{
		if( post.getPostId() == 0 )
		{
			boardService.postAdd(id, post);
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/board/" + id + "?page=1");
			return mv;
		}
		else
		{
			boardService.postModify(id, post);
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/board/" + id + "/view/" + post.getPostId() +"?page=" + page);
			return mv;
		}
	}
	
	
	/** 
	 * 댓글 쓰기
	 */
	@RequestMapping(path = "/board/{id}/comment/{postId}", method = RequestMethod.POST)
	public ModelAndView commentAdd(Model model,
			@PathVariable(value = "id", required = true ) String id,
			@PathVariable(value = "postId", required = true) long postId,
			@RequestParam( name="page", required = false , defaultValue = "1") int page,
			@ModelAttribute("commentForm") Comment comment)
	{
		Board board = boardService.getBoard(id);
		
		Post post = boardService.getPost(board.getTableNumber(), postId);
		
		// path 에 붙어있는거 클래스로 넘김
		comment.setPostId(postId);
		
		// 댓글수 최대치
		if( post.getCommentCount() < Integer.MAX_VALUE ) 
		{
			// 댓글 등록
			boardService.commentAdd(board.getTableNumber(), comment);
			
			// 카운트 증가
			boardService.setPostIncreaseCommentCount(board.getTableNumber(), comment.getPostId(), 1);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/board/" + id + "/view/" + postId +"?page=" + page);
		return mv;
	}
	
	
	/**
	 * api code 
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
	
	 */
	

/**
 *  jade code 
 *  
	@RequestMapping(path = "/board/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_HTML )
	public String boardMain(Model model, @PathVariable( name = "id", required = true) String id )
	{
	
		// JadeTemplate template = JadeConfig.getTemplate("board");
		//return JadeConfig.renderTemplate(template, model.asMap());
	}
*/
	
	
}
