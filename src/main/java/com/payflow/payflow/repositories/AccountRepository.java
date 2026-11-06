package com.payflow.payflow.repositories;

import com.payflow.payflow.entities.Account;
import com.payflow.payflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
