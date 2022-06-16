package com.in28mintues.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28mintues.restfulwebservices.entity.Account;
import com.in28minutes.restfulwebservices.exceptions.UserNotFoundException;

@Service
public class AccountDaoService {

	private static List<Account> accounts = new ArrayList<>();
	private static int usersCount = 3;
	
	static {
		accounts.add(new Account(1, "guy1", "10.12.1990"));
		accounts.add(new Account(2, "guy2", "11.12.1990"));
		accounts.add(new Account(3, "guy3", "12.12.1990"));
	}
	
	public List<Account> findAll() {
		return accounts;
	}
	
	public Account save(Account account) {
		if (account.getId() == null) {
			account.setId(++usersCount);
		}
		accounts.add(account);
		return account;
	}
	
	public Account findOne(int id) {
		Account theUser = accounts.stream()
				  .filter(a -> id == a.getId())
				  .findAny()
				  .orElse(null);
		return theUser;
	}

	public boolean delete(int accountId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Boolean res = accounts.removeIf(e -> e.getId() == accountId);
		if (res == false) {
			throw new UserNotFoundException("delete operation failed");
		}
		return res;
	}
}
