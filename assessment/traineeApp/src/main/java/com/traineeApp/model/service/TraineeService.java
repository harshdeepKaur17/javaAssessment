package com.traineeApp.model.service;

import java.util.List;



import com.traineeApp.model.dao.Trainee;
import com.traineeApp.model.exceptions.DataAccessException;



public interface TraineeService {
public Trainee addTrainee(Trainee trainee);
public List<Trainee> getAll() throws DataAccessException;



}