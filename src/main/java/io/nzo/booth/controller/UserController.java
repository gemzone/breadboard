package io.nzo.booth.controller;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import io.nzo.booth.model.User;
import io.nzo.booth.service.UserService;

@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * 로그인
	 * @param username
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path="/gz/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public String login(Model model, HttpSession session,
			@RequestBody String payload)
	{
		JSONObject result = new JSONObject();
		
		JSONObject params = new JSONObject(payload);
		String username = params.getString("username");
		String password = params.getString("password");
		
		if( session.getAttribute("user") != null )		// 세션 확인
		{
			User user = (User)session.getAttribute("user");
			model.addAttribute(user);
			//session.setAttribute("user", session.getAttribute("user"));	// JSON
			
			result.put("success", true);
			result.put("reason", "이미 인증됨");
		}
		else
		{
			User user = userService.getUserWithLogin(username, password);
			
			if( user != null ) 
			{
				model.addAttribute(user);
				session.setAttribute("user", user);
				// 주의 이거 3개 결과가 다름
				// JSONObject.valueToString(map.get("user"))		
				// new JSONObject(map.get("user")).toString()
				// JSONObject.valueToString(model)
				// map.get("user") // Jade연동하려
				// session.setAttribute("user", map.get("user") );		// JSON
				result.put("success", true);
			}
			else
			{
				session.invalidate();
				logger.debug("");
				
				result.put("success", false);
			}
		}
    	return result.toString();	
	}
	
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
	
	// user add
	@ResponseBody
	@RequestMapping(path = "/gz/user/add", method = RequestMethod.POST)
	public String userAdd(Model model, 
			@RequestParam(value="username", required = true) String username,
			@RequestParam(value="password", required = true) String password,
			@RequestParam(value="name", required = true) String name,
			@RequestParam(value="email", required = true) String email)
	{
		JSONObject result = new JSONObject();
		try
		{
			User user = new User();
			user.setUsername(username);
			user.setPasswordSha2(password);
			user.setName(name);
			user.setEmail(email);
			
			// 중복체크
			User userDuplicate = userService.getUserForUsername(user.getUsername());
			if( userDuplicate == null )
			{
				result.put("success", (userService.create(user) > 0));
			}
			else
			{
				result.put("success", false);
				result.put("error", "Duplicate username");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.put("error", e.getMessage());
			result.put("success", false);
		}
		return result.toString();
	}
	
	
}
