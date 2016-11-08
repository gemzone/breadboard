package io.nzo.booth.controller;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;

@Controller
@SessionAttributes("user")
public class BoardController
{
	@ResponseBody
	@RequestMapping(path="/list", produces="text/html")
	public String create(Model model, HttpSession session)
	{
		
//		userService.createUser();
//		User user = userJpaRepository.findByName("sdfsdfsdf");
//		System.out.println(user.getName());
//		userJpaRepository.create("asdf", "bserb", "123123", "ageewg", "nrtrtnrtn");
//		User user = session.getAttribute("user");
//		model.addAttribute("user", id);
		
		// model.addAttribute("user", "sadfasdf" );
		
		JadeTemplate template = JadeConfig.getTemplate("list");
		String html = JadeConfig.renderTemplate(template, model.asMap());
		return html;
	
	}

}