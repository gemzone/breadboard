package io.nzo.booth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.nzo.booth.common.Paging;
import io.nzo.booth.model.Board;
import io.nzo.booth.model.Post;
import io.nzo.booth.model.User;
import io.nzo.booth.service.UserService;

@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	// 가입
	// /sign/up
	@RequestMapping(path =  "/sign/up", method = RequestMethod.GET, produces = MediaType.TEXT_HTML )
	public String signUp(Model model, HttpServletRequest request,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer)
	{
		User user = new User();
		model.addAttribute("userForm", user);
		
		// referer
		model.addAttribute("ref", "http://naver.com/");
		
		return "signUp";
	}
	
	@RequestMapping(path = "/sign/up", method = RequestMethod.POST)
	public ModelAndView userAdd(Model model,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer,
			@ModelAttribute("userForm") User user)
	{
		// 중복체크
		User userDuplicate = userService.getUserForUsername(user.getUsername());
		if( userDuplicate == null )
		{
			userService.create(user);
			
			user.setPasswordSha2("");
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/");
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/error");
			return mv;
		}
	}
	
	// 로그인
	// /sign/in
	@RequestMapping(path =  "/sign/in", method = RequestMethod.GET, produces = MediaType.TEXT_HTML )
	public String signIn(Model model, HttpServletRequest request,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer)
	{
		User user = new User();
		model.addAttribute("userForm", user);
		
		// referer
		model.addAttribute("ref", "http://naver.com/");
		
		return "signIn";
	}
	
	@RequestMapping(path = "/sign/in", method = RequestMethod.POST)
	public ModelAndView userLogin(Model model, HttpSession session,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer,
			@ModelAttribute("userForm") User user)
	{
		if( session.getAttribute("user") != null )		// 세션 확인
		{
			// nothing
			User sessionUser = (User)session.getAttribute("user");
			model.addAttribute(sessionUser);

			// 로그인성공
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:http://naver.com");
			return mv;
		}
		else
		{
			User loginUser = userService.getUserWithLogin(user.getUsername(), user.getPasswordSha2());
			
			if( loginUser != null ) 
			{
				model.addAttribute(loginUser);
				session.setAttribute("user", loginUser);
				
				// 로그인성공
				ModelAndView mv = new ModelAndView();
				mv.setViewName("redirect:http://naver.com");
				return mv;
			}
			else
			{
				session.invalidate();
				
				// 로그인실패
				ModelAndView mv = new ModelAndView();
				mv.setViewName("redirect:/");
				return mv;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 로그아웃
	// /sign/out
	
	
	/**
	 * 로그인
	 * @param username
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(path="/gz/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
//	public String login(Model model, HttpSession session,
//			@RequestBody String payload)
//	{
//		JSONObject result = new JSONObject();
//		
//		JSONObject params = new JSONObject(payload);
//		String username = params.getString("username");
//		String password = params.getString("password");
//		
//		if( session.getAttribute("user") != null )		// 세션 확인
//		{
//			User user = (User)session.getAttribute("user");
//			model.addAttribute(user);
//			//session.setAttribute("user", session.getAttribute("user"));	// JSON
//			
//			result.put("success", true);
//			result.put("reason", "이미 인증됨");
//		}
//		else
//		{
//			User user = userService.getUserWithLogin(username, password);
//			
//			if( user != null ) 
//			{
//				model.addAttribute(user);
//				session.setAttribute("user", user);
//				// 주의 이거 3개 결과가 다름
//				// JSONObject.valueToString(map.get("user"))		
//				// new JSONObject(map.get("user")).toString()
//				// JSONObject.valueToString(model)
//				// map.get("user") // Jade연동하려
//				// session.setAttribute("user", map.get("user") );		// JSON
//				result.put("success", true);
//			}
//			else
//			{
//				session.invalidate();
//				logger.debug("");
//				
//				result.put("success", false);
//			}
//		}
//    	return result.toString();	
//	}
	
	
	
	/**
	 * 로그아웃 
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/gz/logout", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON)
	public String logout(Model model, HttpSession session)
	{
		JSONObject result = new JSONObject();
		
		session.invalidate();
		
		result.put("success", true);
		return result.toString();
	}
	
	
}
