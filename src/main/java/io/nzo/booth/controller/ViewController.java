package io.nzo.booth.controller;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;
import io.nzo.booth.service.BoardService;
import io.nzo.booth.service.UserService;

@Controller
@SessionAttributes("user")
public class ViewController
{
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	BoardService boardService;
	
	
	@ResponseBody
	@RequestMapping(path = "/", produces = MediaType.TEXT_HTML)
	public String board(Model model) 
	{
		JadeTemplate template = JadeConfig.getTemplate("board");
		return JadeConfig.renderTemplate(template, model.asMap());
	}
	
	@ResponseBody
	@RequestMapping(path = "/e/list", produces = MediaType.TEXT_HTML)
	public String list(Model model) 
	{
		JadeTemplate template = JadeConfig.getTemplate("list");
		return JadeConfig.renderTemplate(template, model.asMap());
	}
	
	@ResponseBody
	@RequestMapping(path = "/e/view", produces = MediaType.TEXT_HTML)
	public String view(Model model)
	{
		JadeTemplate template = JadeConfig.getTemplate("view");
		return JadeConfig.renderTemplate(template, model.asMap());
	}
	
	@ResponseBody
	@RequestMapping(path = "/e/write", produces = MediaType.TEXT_HTML)
	public String write(Model model)
	{
		JadeTemplate template = JadeConfig.getTemplate("write");
		return JadeConfig.renderTemplate(template, model.asMap());
	}
	
	

	@ResponseBody
	@RequestMapping(path = "/login", produces = MediaType.TEXT_HTML)
	public String login(Model model) 
	{
		JadeTemplate template = JadeConfig.getTemplate("login");
		return JadeConfig.renderTemplate(template, model.asMap());
	}
	
}
