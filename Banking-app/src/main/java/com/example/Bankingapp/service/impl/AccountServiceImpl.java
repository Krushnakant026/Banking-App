package com.example.Bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bankingapp.dto.AccountDto;
import com.example.Bankingapp.entity.Account;
import com.example.Bankingapp.mapper.AccountMapper;
import com.example.Bankingapp.repo.AccountRepo;
import com.example.Bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepo accountrepo;
	
	public AccountServiceImpl(AccountRepo accountrepo) {
		this.accountrepo=accountrepo;
	}


	@Override
	public AccountDto createAccount(AccountDto accountdto) {
		Account account = AccountMapper.mapToAccount(accountdto);
		Account save = accountrepo.save(account);
		return AccountMapper.maptoAccountDto(save);
	}
	
	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountrepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not found"));
		return AccountMapper.maptoAccountDto(account);
	}
	
	@Override
	public AccountDto deposit(Long id,double amount) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account does not found"));
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account save = accountrepo.save(account);
		return AccountMapper.maptoAccountDto(save);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account does not found"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Amount");
		}
		
		double remaining = account.getBalance()-amount;
		account.setBalance(remaining);
		Account save = accountrepo.save(account);
		
		return AccountMapper.maptoAccountDto(save);
	}

	@Override
	public List<AccountDto> getAllAccount() {
		
		List<Account> accounts = accountrepo.findAll();
		return accounts.stream().map((account) -> AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
		
	}


	@Override
	public void delete(Long id) {
		Account account = accountrepo.findById(id).orElseThrow(()-> new RuntimeException("Account does not found"));
		
		accountrepo.deleteById(id);
		
		
	}
}
