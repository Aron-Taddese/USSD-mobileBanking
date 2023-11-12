package com.gebeya.projectone.First.Project.repositories;

import com.gebeya.projectone.First.Project.beans.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}
