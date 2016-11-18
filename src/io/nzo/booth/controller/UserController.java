package io.nzo.booth.controller;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;
import io.nzo.booth.common.ResultValidation;
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
	@RequestMapping(path="/login", produces="application/json")
	public String login(@RequestParam(value="username", required=false) String username,
			@RequestParam(value="password", required=false) String password,
			Model model, HttpSession session)
	{
		if( session.getAttribute("user") != null )		// 세션 확인
		{
			User user = (User)session.getAttribute("user");
			model.addAttribute(user);
			//session.setAttribute("user", session.getAttribute("user"));	// JSON
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
			}
			else
			{
				session.invalidate();
				logger.debug("");
			}
		}
    	return JSONObject.valueToString(model);	
	}
	
	/**
	 * 로그아웃 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(Model model, HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:/list");
		return mv;
	}
}
