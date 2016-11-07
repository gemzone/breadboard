package io.nzo.booth.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.nzo.booth.model.User;
import io.nzo.booth.service.UserService;
import io.nzo.orm.HibernateUtil;

@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(path="/login", produces="text/json")
	public String login(Model model, HttpSession httpSession)
	{
//    	User user = (User)session.get(User.class, 1L);
//    	System.out.println( user.getName() );
    	
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
    	// select case 1
        List<User> users = session.createNativeQuery("SELECT TOP 1 u.* FROM gz_user u WHERE u.username=:username")
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
    	
//    	Long newIdentity = userJpaRepository.getNewIdentity();
//		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		Transaction transaction = session.getTransaction();
//		
//		transaction.begin();
//		
//		try
//		{
//			String sql = "INSERT INTO [dbo].[user] ([user_id], [group_id], [id], [password_sha2_512], [password_sha3], [name], [email], [level], [point], [comment], [is_superadmin]) "   
//					+ "VALUES (:newIdentity, 0, :id ,HASHBYTES('SHA2_512' , :password ), NULL, :name, :email, 9, 0, :comment, 0) ";
//			Query query = session.createSQLQuery(sql);
//			
//			query.setParameter("newIdentity", newIdentity);
//			query.setParameter("id", id);
//			query.setParameter("password", password);
//			query.setParameter("name", name);
//			query.setParameter("email", email);
//			query.setParameter("comment", comment);
//			query.executeUpdate();
//			
//			
//		//	int a = 0 / 0 ;
//			
//			transaction.commit();
//		}
//		catch(Exception e)
//		{
//			transaction.rollback();
//		}
    	
    	model.addAttribute("success", true);
    	model.addAttribute("success", false);
    	
    	return new JSONObject(model).toString();
	}
	
	
	@ResponseBody
	@RequestMapping(path="/test123", produces="text/json")
	public String test123(Model model, HttpSession httpSession)
	{
		User user = userService.addUser("test123", "password123", "name", "email", "comment");
		
		if( user != null )
		{
			model.addAttribute(user);
			
		}
		
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
