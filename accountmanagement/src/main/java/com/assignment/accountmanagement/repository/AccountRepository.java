package com.assignment.accountmanagement.repository;

import com.assignment.accountmanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomerId(Long customerId);
}
