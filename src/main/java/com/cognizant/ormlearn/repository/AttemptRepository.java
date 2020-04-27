package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
	
	@Query(value = "SELECT atm FROM Attempt as atm join fetch atm.user WHERE atm.id=?2 AND atm.user.id=?1")
	public Attempt getAttempt(int userId, int attemptId);
	
}