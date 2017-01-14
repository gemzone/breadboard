package io.nzo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.breadboard.JadeConfig;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test-temp")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
		response.setHeader("Last-Modified", new Date().toString());
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");	// HTTP 1.1
		response.setHeader("Pragma", "no-cache");	// HTTP 1.0
		// response.setContentType("application/json");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		
		request.getSession().setAttribute("asdfasdf", "asdfasdf");
		
		// System.getProperty
		
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        try
//        {
//            User user = new User();
//            user.setUsername("sdf");
//            user.setPasswordSha2("sdfsdf");
//            user.setName("sadfasdfasdfd");
//            user.setEmail("asdfasdv@sdvsdv");
//            user.setComment("코멘트 테스트");
//            user.setHomepage("homepage");
//            user.setCreationTime(new Date());
//            session.save(user);
//            tx.commit();
//        }
//        catch(Exception e)
//        {
//        	tx.rollback();
//        }

        JadeTemplate template = JadeConfig.getTemplate("index");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("company", "neuland");
        
        String html = JadeConfig.renderTemplate(template, model);
		
		out.print(html);
		
	}

}
