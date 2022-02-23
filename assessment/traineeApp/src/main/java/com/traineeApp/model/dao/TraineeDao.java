package com.traineeApp.model.dao;

import java.util.List;
import java.util.Optional;



import com.traineeApp.model.exceptions.DataAccessException;
import com.traineeApp.model.exceptions.TraineeNotFoundException;



public interface TraineeDao {



public Trainee addTrainee(Trainee trainee);

public List<Trainee> getAll() throws DataAccessException;




}