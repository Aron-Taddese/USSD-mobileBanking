package com.gebeya.projectone.First.Project.repositories;

import com.gebeya.projectone.First.Project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
