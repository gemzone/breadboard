package io.nzo.breadboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import io.nzo.breadboard.service.BoardService;
import io.nzo.breadboard.service.UserService;

@Controller
@SessionAttributes("user")
public class AdminController
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	BoardService boardService;
	
	
	// /install
	@RequestMapping(path =  "/install", method = { RequestMethod.GET }, produces = MediaType.TEXT_HTML )
	public String install(Model model, HttpServletRequest request)
	{
		return "install";
	}
	
	
	// /admin/login
	@RequestMapping(path =  "/admin/login", method = { RequestMethod.GET }, produces = MediaType.TEXT_HTML )
	public String login(Model model, HttpServletRequest request)
	{
		return "admin";
	}
	
	
	// /admin/logout
	@RequestMapping(path =  "/admin/logout", method = { RequestMethod.GET }, produces = MediaType.TEXT_HTML )
	public String logout(Model model, HttpServletRequest request)
	{
		return "admin";
	}
	
	// /admin
	@RequestMapping(path =  "/admin", method = { RequestMethod.GET }, produces = MediaType.TEXT_HTML )
	public String main(Model model, HttpServletRequest request)
	{
		return "admin";
	}
	
	
    @RequestMapping(path = "/authorization-code" )
	@ResponseBody
	public String authorizationCodeTest(@RequestParam("code") String code) {
		String curl = String.format("curl " +
				"-F \"grant_type=authorization_code\" " +
				"-F \"code=%s\" " +
				"-F \"scope=read\" " +
				"-F \"client_id=data\" " +
				"-F \"client_secret=test\" " +
				"-F \"redirect_uri=https://test.nzo.io/breadboard/authorization-code\" " +
				"\"http://data:test@test.nzo.io/oauth/token\"", code);
		return curl;
	}
}
