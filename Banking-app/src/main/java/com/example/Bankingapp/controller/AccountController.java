package com.example.Bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bankingapp.dto.AccountDto;
import com.example.Bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	private AccountService accountservice;

	public AccountController(AccountService accountservice) {
		this.accountservice = accountservice;
	}
	
	//Add Account REST API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountdto){
		return new ResponseEntity<>(accountservice.createAccount(accountdto),HttpStatus.CREATED);
	}
	
	//Get Account REST Api
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountdto = accountservice.getAccountById(id);
		return ResponseEntity.ok(accountdto);
	}
	
	//Deposit REST Api
	@PutMapping("{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
		Double amount = request.get("amount");
		AccountDto accountdto = accountservice.deposit(id,amount);
		return ResponseEntity.ok(accountdto);
	}
	
	//Withdraw REST Api
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
		
		double amount = request.get("amount");
		AccountDto accountdto = accountservice.withdraw(id, amount);
		return ResponseEntity.ok(accountdto);
	}
	
	//Get All Account REST Api
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccount(){
		List<AccountDto> accounts = accountservice.getAllAccount();
		return ResponseEntity.ok(accounts);
	}
	
	//Delete REST Api
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		accountservice.delete(id);
		return ResponseEntity.ok("Account Deleted");
	}
}
