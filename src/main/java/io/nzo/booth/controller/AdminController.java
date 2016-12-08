package io.nzo.booth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import io.nzo.booth.service.BoardService;
import io.nzo.booth.service.UserService;

@Controller
@SessionAttributes("user")
public class AdminController
{
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired UserService userService;
	@Autowired BoardService boardService;
	
	@RequestMapping(path =  "/admin", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_HTML )
	public String main(Model model, HttpServletRequest request)
	{
		return "admin";
	}
	
}
