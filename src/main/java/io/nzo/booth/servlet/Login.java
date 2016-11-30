package io.nzo.booth.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.neuland.jade4j.template.JadeTemplate;
import io.nzo.booth.JadeConfig;

/**
 * Servlet implementation class Test
 */
@WebServlet("/login-temp")
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login()
	{
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		JadeTemplate template = JadeConfig.getTemplate("login");
		String html = JadeConfig.renderTemplate(template, new HashMap<String, Object>());
		out.print(html);
	}
	
}
