package com.model.persistence.trainee;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.exceptions.DataAccessException;
import com.model.exceptions.TraineeNotFoundException;
import com.model.persistence.factory.HibernateSessionFactory;

public class TraineeDaoImpl implements TraineeDao{

	private SessionFactory factory;
	
	public TraineeDaoImpl() {
		factory = HibernateSessionFactory.getSessionFactory();
	}

	@Override
	public Trainee addTrainee(Trainee trainee) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.save(trainee);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
		return trainee;
	}

	@Override
	public void deleteTrainee(int id) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Trainee trainee = getById(id).orElseThrow(()->new TraineeNotFoundException("Trainee with id "+id+" not found"));
			session.delete(trainee);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
	}

	@Override
	public Trainee updateTrainee(int id, Trainee trainee) {
		Session session = factory.getCurrentSession();
		Transaction tx = session.getTransaction();
		Trainee temp = null;
		try {
			tx.begin();
			temp = getById(id).orElseThrow(()->new TraineeNotFoundException("Trainee with id "+id+" not found"));
			temp.setName(trainee.getName());
			temp.setBranch(trainee.getBranch());
			temp.setPercentage(trainee.getPercentage());
			session.update(temp);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
		return temp;
	}

	@Override
	public List<Trainee> getAll() throws DataAccessException {
		Session session = factory.getCurrentSession();
		Transaction tx = session.getTransaction();
		List<Trainee> trainee = null;
		try {
			tx.begin();
			trainee = session.createQuery("select t from Trainee t",Trainee.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
		return trainee;
	}
	@Override
	public Optional<Trainee> getById(int id) throws TraineeNotFoundException {
		Session session = factory.openSession();
		Trainee trainee = session.get(Trainee.class, id);
		session.close();
		if(trainee == null)
			throw new TraineeNotFoundException("Trainee with id "+id+" not found");
		return Optional.ofNullable(trainee);
	}
	
	
}
