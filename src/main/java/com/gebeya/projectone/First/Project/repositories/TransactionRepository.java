package com.gebeya.projectone.First.Project.repositories;

import com.gebeya.projectone.First.Project.beans.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
