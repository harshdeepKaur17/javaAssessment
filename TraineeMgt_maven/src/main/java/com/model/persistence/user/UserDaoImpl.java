package com.model.persistence.user;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.model.persistence.factory.HibernateSessionFactory;

public class UserDaoImpl implements UserDao{

	private SessionFactory factory;
	
	public UserDaoImpl() {
		factory = HibernateSessionFactory.getSessionFactory();
	}
	
	@Override
	public void addUser(User user) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.save(user);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
	}

	@Override
	public Optional<User> getUser(String username, String password){
		Session session = factory.getCurrentSession();
		Transaction tx = session.getTransaction();
		List<User> user = null;
		try {
			tx.begin();
			user = session.createQuery("select u from User u where u.username=:username and"
					+ " u.password=:password",User.class)
			.setParameter("username",username)
			.setParameter("password", password)
			.getResultList();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
		return Optional.ofNullable(user.get(0));
	}

}
