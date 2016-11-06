package io.nzo.booth.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.nzo.booth.model.User;
import io.nzo.orm.HibernateUtil;

@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ResponseBody
	@RequestMapping(path="/login", produces="text/json")
	public String login(Model model, HttpSession httpSession)
	{

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
//    	User user = (User)session.get(User.class, 1L);
//    	System.out.println( user.getName() );
    	
//    	String countQ = "Select count (f.id) from Foo f";
//    	Query countQuery = session.createQuery(countQ);
//    	Long countResults = (Long) countQuery.uniqueResult();
    	
    	// TODO sqlserver
        
    	
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<User> users = session.createNativeQuery("SELECT TOP 1 u.* FROM gz_user u WHERE u.username=:username" )
			.addEntity("u", User.class)
    		.setParameter("username", "sdf")
    		.getResultList();
    	
    	if( users.size() > 0 )
    	{
    		Iterator<User> iter = users.iterator();
    		if( iter.hasNext() ) 
    		{
    			User user = iter.next();
    			
    			
    			System.out.println(user.getName() + " " + user.getUserId());
    			
    		}
    	}
    	model.addAttribute("success", true);
    	model.addAttribute("success", false);
    	
    	return JSONObject.valueToString(model);
	}

}










//List<User> userList = session.createSQLQuery("SELECT TOP 1 * FROM gz_user WHERE username=:username")
//		.  .list();
//System.out.println( userList.size() );
//for( User u : userList )
//{
//	System.out.println(u.getName() + " " + u.getUserId());
//}

//List<User> list = session.createCriteria(User.class)
//		.add(Restrictions.like("username", "sdf"))
//		.list();
//
//
//List<User> list = session.createCriteria(User.class).list();


//String sql = "SELECT * FROM gz_user WHERE username LIKE 'sdf'";
//SQLQuery query = session.createSQLQuery(sql);
//query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//List<User> list = query.list();


//Query<User> q = session.createQuery("from gz_user user where user.username=:username");
//q.setParameter("username", "sdf"); 
//List<User> list = q.list();
