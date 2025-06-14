package com.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankingapp.dto.AccountDto;
import com.bankingapp.entity.Account;
import com.bankingapp.mapper.AccountMapper;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.service.AccountService;
@Service

public class AccountServiceImpl implements AccountService{
	private AccountRepository accountRepository;
	

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposite(Long id, double amount) {
		Account account = accountRepository
				.findById(id).
				orElseThrow(()->new RuntimeException("Account does not exist"));
		
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exist"));
		
		if(account.getBalance()< amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
			double total = account.getBalance() - amount;
			account.setBalance(total);
			Account savedAccount = accountRepository.save(account);
			
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
	}

}
