package com.bankingapp.controller;




import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.AccountDto;
import com.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		//super();
		this.accountService = accountService;
	}
	
	//add Account Rest API
	@PostMapping
	public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto  accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	

	// Get Account REST API
	 @GetMapping("/{id}")
	public ResponseEntity<AccountDto>getAccountById(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	 
	 //Deposite REST API
	 @PutMapping("/{id}/deposite")
	 public ResponseEntity<AccountDto>deposite(@PathVariable  Long id,
			                                    @RequestBody Map<String, Double>request){
		 
		 Double amount = request.get("amount");
		 
		AccountDto accountDto = accountService.deposite(id, request.get("amount"));
		 return ResponseEntity.ok(accountDto);
	 }
	 
	 //Withraw REST API
	 
	 @PutMapping("/{id}/withdraw")
	 public ResponseEntity<AccountDto>withraw( @PathVariable Long id,
			                               @RequestBody Map<String,Double>request){
		 
		 double amount = request.get("amount");
		 AccountDto accountDto = accountService.withdraw(id, amount);
		 return ResponseEntity.ok(accountDto);
		 
	 }
	 
	 //GET ALL ACCOUNT REST API
	 @GetMapping
	 public ResponseEntity<List<AccountDto>> getAllAccounts(){
		 List<AccountDto> accounts = accountService.getAllAccounts();
		 return ResponseEntity.ok(accounts);
	 }
}
