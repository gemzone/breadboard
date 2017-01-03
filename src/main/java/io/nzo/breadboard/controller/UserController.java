package io.nzo.breadboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import io.nzo.breadboard.domain.User;
import io.nzo.breadboard.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	// 가입
	// /register
	@RequestMapping(path =  "/register", method = RequestMethod.GET, produces = MediaType.TEXT_HTML )
	public String register(Model model, HttpServletRequest request,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer)
	{
		User user = new User();
		model.addAttribute("userForm", user);
		
		// referer
		model.addAttribute("ref", "http://google.com/");
		
		return "register";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(Model model,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer,
			@Valid @ModelAttribute("userForm") User user,
			BindingResult bindingResult)
	{
        if (bindingResult.hasErrors()) 
        {
        	System.out.println( bindingResult.toString() );
            return "register";
        }
        else
        {
        	// 중복체크
    		User userIdDuplicate = userService.getUserForUsername(user.getUsername());
    		
    		User userEmailDuplicate = userService.getUserForEmail(user.getEmail());
    		
    		if( userIdDuplicate != null )
    		{
    			bindingResult.addError( new FieldError("username", "username", "이미 존재하는 아이디 입니다")  );
            	return "register";
    			
    		}
    		else if( userEmailDuplicate != null )
    		{
    			bindingResult.addError( new FieldError("email", "email", "이미 등록된 이메일 입니다")  );
            	return "register";
    		}
    		else
    		{
    			userService.create(user);
    			user.setPasswordSha2("");
    			return "redirect:/register";
    		}
        }
	}
	
	// 로그인
	// /login
	@RequestMapping(path =  "/login", method = RequestMethod.GET, produces = MediaType.TEXT_HTML )
	public String login(Model model, HttpServletRequest request,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer)
	{
		User user = new User();
		model.addAttribute("userForm", user);
		
		// referer
		model.addAttribute("ref", "http://google.com/");
		
		return "login";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer,
			@Valid @ModelAttribute("userForm") User user,
			BindingResult bindingResult)
	{
		if( session.getAttribute("user") != null )		// 세션 확인
		{
			// nothing
			User sessionUser = (User)session.getAttribute("user");
			model.addAttribute(sessionUser);

			System.out.println("이미 로그인됨" + sessionUser.getName());
			
			return "login";
		}
		else
		{
			User loginUser = userService.getUserWithLogin(user.getUsername(), user.getPasswordSha2());
			if( loginUser != null ) 
			{
				model.addAttribute(loginUser);
				session.setAttribute("user", loginUser);
				
				System.out.println("로그인 성공" + loginUser.getName());
				return "login";
			}
			else
			{
				session.invalidate();

				if(model.containsAttribute("user"))
				{
					model.asMap().remove("user");
				}
				
				bindingResult.addError( new FieldError("username", "username", "아이디 또는 비밀번호가 올바르지 않습니다.")  );
				return "login";
			}
		}
	}
	
	// 로그아웃
	@RequestMapping(path = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(Model model, HttpSession session,
			@RequestParam( name="ref", required = false , defaultValue = "") String referer)
	{
		session.invalidate();
		
		if(model.containsAttribute("user"))
		{
			model.asMap().remove("user");
		}
		
		return "redirect:/login";
	}
}
