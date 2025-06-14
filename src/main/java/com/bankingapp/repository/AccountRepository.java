package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}

