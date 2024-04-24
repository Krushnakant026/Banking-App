package com.example.Bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bankingapp.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

}
