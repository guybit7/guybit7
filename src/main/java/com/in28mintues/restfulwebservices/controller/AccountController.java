package com.in28mintues.restfulwebservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28mintues.restfulwebservices.dao.AccountDAO;
import com.in28mintues.restfulwebservices.dao.AccountDaoService;
import com.in28mintues.restfulwebservices.dao.PostDAO;
import com.in28mintues.restfulwebservices.entity.Account;
import com.in28mintues.restfulwebservices.entity.Post;
import com.in28minutes.restfulwebservices.exceptions.UserNotFoundException;

@RestController
@Component
public class AccountController {
	
	@Autowired
	private AccountDaoService daoService;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private MessageSource messageSource; 
	
	public AccountController() {
		System.out.println("---> AccountController");
	}
	
	
//	@GetMapping("/hello-world")
//	public String helloWorld(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
//		return messageSource.getMessage("good.morning.message", null, locale);
//	}
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/jpa/accounts")
	public List<Account> retreiveAllAccounts() {
		return accountDAO.findAll();
	}
	
	@GetMapping("/jpa/account/{id}")
	public Account getAccount(@PathVariable int id) {
		Optional<Account> theUser = accountDAO.findById(id);
		if (!theUser.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		return theUser.get();
	}
	
	@PostMapping("/jpa/account")
	public ResponseEntity<Account> postAccount(@Valid @RequestBody Account account) {
		Account a = accountDAO.save(account);
		return ResponseEntity.ok(a);
	}
	
	@DeleteMapping("/jpa/account/{id}")
	public void deleteUser(@PathVariable int id) {
		accountDAO.deleteById(id);
	}
	
	@GetMapping("/jpa/account/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<Account> theUser = accountDAO.findById(id);
		if (!theUser.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		return theUser.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, @PathVariable int id) {
		Optional<Account> theAccount = accountDAO.findById(id);
		if (!theAccount.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		Account account = theAccount.get();
		post.setAccount(account);
		Post p = postDAO.save(post);
		
		return ResponseEntity.ok(p);
	}
	
	
}
