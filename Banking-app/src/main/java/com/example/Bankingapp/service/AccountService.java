package com.example.Bankingapp.service;

import java.util.List;

import com.example.Bankingapp.dto.AccountDto;


public interface AccountService {
	
	AccountDto createAccount(AccountDto accountdto);
	AccountDto getAccountById(Long id);
	AccountDto deposit(Long id,double amount);
	AccountDto withdraw(Long id,double amount);
	List<AccountDto> getAllAccount();
	void delete(Long id);
}
