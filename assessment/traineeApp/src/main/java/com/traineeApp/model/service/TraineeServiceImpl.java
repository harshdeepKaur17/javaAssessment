package com.traineeApp.model.service;

import java.util.*;

import com.traineeApp.model.dao.Trainee;
import com.traineeApp.model.dao.TraineeDao;
import com.traineeApp.model.dao.TraineeDaoImpl;
import com.traineeApp.model.exceptions.DataAccessException;

public class TraineeServiceImpl implements TraineeService {

	private TraineeDao traineeDao;

	public TraineeServiceImpl() {
		traineeDao = new TraineeDaoImpl();
	}

	public Trainee addTrainee(Trainee trainee) {

		return traineeDao.addTrainee(trainee);
	}

	public List<Trainee> getAll() throws DataAccessException {
		List<Trainee> trainees = new ArrayList<Trainee>();
		trainees = traineeDao.getAll();
		return trainees;
	}
}
