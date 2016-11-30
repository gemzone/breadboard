package io.nzo.booth.service;

import java.math.BigInteger;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import io.nzo.booth.controller.UserController;
import io.nzo.booth.model.User;
import io.nzo.orm.HibernateUtil;

@Component
public class UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
//	Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);
//	City getCity(String name, String country);
//	Page<HotelSummary> getHotels(City city, Pageable pageable);
	
	/**
	 * 유저 신규 등록
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param comment
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public int create(User user)
	{
		int update = 0;
		
		try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{

			Transaction tx = session.beginTransaction();
//			Long newIdentity = 0L;
//	        {
//	        	NativeQuery query = session.createNativeQuery("SELECT (ISNULL(MAX([user_id]), 0) + 1) FROM gz_user");
//	        	newIdentity = ((BigInteger) query.getSingleResult()).longValue();
//	        }
	        
//			int executeUpdate()
//			Execute an update or delete statement.
//			Returns:
//				the number of entities updated or deleted
//			Throws:
//				IllegalStateException - if called for a Java Persistence query language SELECT statement or for a criteria query
//				TransactionRequiredException - if there is no transaction
//				QueryTimeoutException - if the statement execution exceeds the query timeout value set and only the statement is rolled back
//				PersistenceException - if the query execution exceeds the query timeout value set and the transaction is rolled back
	        {
				String sql = "INSERT INTO gz_user (username, password_sha2, name, email ) " +
							"VALUES (:username, HASHBYTES('SHA2_512',:password ), :name, :email) "; 
				NativeQuery query = session.createNativeQuery(sql);
				query.setParameter("username", user.getUsername());
				query.setParameter("password", user.getPasswordSha2());
				query.setParameter("name", user.getName());
				query.setParameter("email", user.getEmail());
				update = query.executeUpdate();
			}
			tx.commit();
		} 
		catch (Exception e)
		{
			update = 0;
			e.printStackTrace();
		}
		return update;
		
//		user = (User)session.get(User.class, newIdentity);
//		map.addAttribute("user", user);
	}
	
	/**
	 * 로그인 인증
	 * @param username
	 * @param password
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public User getUserWithLogin(String username, String password)
	{
        try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			String sql = "SELECT TOP 1 u.* FROM gz_user u WHERE u.username=:username AND u.password_sha2=HASHBYTES('SHA2_512',:password) "; 
			NativeQuery query = session.createNativeQuery(sql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			User user = (User)query.getSingleResult();
			user.setPasswordSha2("");
			return user;
		} 
		catch (NoResultException e)
		{
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public User getUser(Long userId) 
	{
        try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
        	if( userId == null )
        	{
        		return null;
        	} 
        	else 
        	{
        		return (User)session.get(User.class, userId);
        	}
		} 
		catch (NoResultException e)
		{
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	// 아이디 확인
	public User getUserForUsername(String username) 
	{
        try ( Session session = HibernateUtil.getSessionFactory().openSession() )
		{
			String sql = "SELECT TOP 1 u.* FROM gz_user u WHERE u.username=:username "; 
			@SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery(sql, User.class);
			query.setParameter("username", username);
			User user = (User)query.getSingleResult();
			user.setPasswordSha2("");
			return user;
		} 
		catch (NoResultException e)
		{
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	private final UserJpaRepository userJpaRepository;
//
//
//	public UserServiceImpl(UserJpaRepository userJpaRepository) 
//	{
//		this.userJpaRepository = userJpaRepository;
//	}
//	
//	@Transactional
//	public long createUser()
//	{
//		// memberJpaRepository.create("asdasasc", "bdfdfbd", "q3rqwrwr", "dsbrfdfb", "dbfdfbdfbbfd");
//		
//		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction = session.getTransaction();
//		try 
//		{
//			transaction.begin();
//			
//			
//			
////			
////			String sql = "SELECT (ISNULL(MAX([user_id]), 0) + 1) AS [user_id] FROM [dbo].[user]";
////			Query query = session.createSQLQuery(sql);
////			
////			System.out.println( "sadfasdfs" + query.getQueryString() () );
////			
////			
//			
//			//query.executeUpdate();
//			
//			transaction.commit();
//		}
//		catch(Exception e)
//		{
//			transaction.rollback();
//			log.error(e.getMessage());
//		}
//		finally
//		{
//			session.close();
//		}
//		
////		
////		
////		
////		INSERT INTO [dbo].[user] ([user_id], [group_id], [id], [password_sha2_512], [password_sha3], [name], [email], [level], [point], [comment], [is_superadmin])  
////		VALUES ((SELECT (ISNULL(MAX([user_id]), 0) + 1) FROM [dbo].[user]), 0, :id ,HASHBYTES('SHA2_512' , :password ), NULL, :name, :email, 9, 0, :comment, 0) 
////		
////		
//		
//		
////		
////		entityManager.createNativeQuery( ).setParameter(arg0, arg1);
////		
////		query.
//		
////		Session session = HibernateUtil.getSessionFactory().openSession();
////        session.beginTransaction();
////		
////        try
////        {
////            //Update record using named query
////            Query query = session. (DepartmentEntity.UPDATE_DEPARTMENT_BY_ID)
////                                        .setInteger("id", 1)
////                                        .setString("name", "Finance");
////            query.executeUpdate();
////             
////            //Get named query instance
////            query = session.getNamedQuery(DepartmentEntity.GET_DEPARTMENT_BY_ID)
////                                        .setInteger("id", 1);
////            //Get all departments (instances of DepartmentEntity)
////            DepartmentEntity department = (DepartmentEntity) query.uniqueResult();
////            System.out.println(department.getName());
////            
////            
////            
////        }
////        finally
////        {
////            session.getTransaction().commit();
////            session.close();
////        }
////        
////        
////
////		@Query(value = "INSERT INTO [dbo].[user] ([user_id], [group_id], [id], [password_sha2_512], [password_sha3], [name], [email], [level], [point], [comment], [is_superadmin]) " 
////				+ "VALUES ((SELECT (ISNULL(MAX([user_id]), 0) + 1) FROM [dbo].[user]), 0, :id ,HASHBYTES('SHA2_512' , :password ), NULL, :name, :email, 9, 0, :comment, 0) "
////				+ "SELECT TOP 1 * FROM [dbo].[user] WHERE [user_id] = (SELECT (ISNULL(MAX([user_id]), 0) ) FROM [dbo].[user]) ", 
////				nativeQuery = true )
////		public User create(@Param("id") String id,
////				@Param("password") String password,
////				@Param("name") String name,
////				@Param("email") String email,
////				@Param("comment") String comment);
////		
////		
////		User m = new User();
////		m.setUserId(123);
////		m.setId("sdfsdfsD");
////		m.setName("sdfsdfsdf");
////		m.setEmail("sdfsdfsdfsd@asd.com");
////		m.setComment("sdfsdfsdfsd");
////		
////		User newUser = userJpaRepository.save( m );
////		// System.out.println(newMember.getMemberId());
////		
////		// int error = 0 / 0 ;
//		
//		// return newUser.getUserId();
//		return 1L;
//	}

	

	
}