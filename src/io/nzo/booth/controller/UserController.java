package io.nzo.booth.controller;

import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;
import io.nzo.booth.model.User;
import io.nzo.orm.HibernateUtil;

@Controller
@EnableAutoConfiguration
public class UserController
{
	@ResponseBody
	@RequestMapping(path="/login", produces="text/json")
	public String create(Model model, HttpSession httpSession)
	{
		
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try
        {
        	User user = session.find(User.class, 1);
        	
        	System.out.println( user.getName() );
        }
        catch(Exception e)
        {
        	tx.rollback();
        }

        
        
        
//		userService.createUser();
//		User user = userJpaRepository.findByName("sdfsdfsdf");
//		System.out.println(user.getName());
//		userJpaRepository.create("asdf", "bserb", "123123", "ageewg", "nrtrtnrtn");
//		User user = session.getAttribute("user");
//		model.addAttribute("user", id);
		
		try
		{
			JadeTemplate template = JadeConfig.getTemplate("list");
			String html = JadeConfig.renderTemplate(template, model.asMap());
			return html;
		}
		catch(Exception e)
		{
			return "";
		}
	}

}