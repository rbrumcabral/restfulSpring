package com.brum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brum.domain.entities.User;
import com.brum.domain.entities.UserExpenses;

@Repository
public interface UserExpensesRepository extends JpaRepository<UserExpenses, Long> {
}
