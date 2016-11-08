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
import io.nzo.booth.service.UserService;

@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(path="/login", produces="text/json")
	public String login(@RequestParam(value="username", required=false) String username,
			@RequestParam(value="password", required=false) String password,
			Model model, HttpSession session)
	{
		if( session.getAttribute("user") != null )
		{
			model.addAttribute(session.getAttribute("user"));
		}
		else
		{
			ModelMap map = userService.getUserWithLogin(username, password);
			if( ResultValidation.check(map) ) 
			{
				model.addAttribute(map.get("user"));
				session.setAttribute("user", map.get("user"));		// 세션 등록
			}
			else
			{
				logger.debug( map.get("reason").toString());
			}
		}
    	return JSONObject.valueToString(model);	
	}

	@RequestMapping("/logout")
	public ModelAndView logout(Model model, HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:/login");
		return mv;
	}
}
