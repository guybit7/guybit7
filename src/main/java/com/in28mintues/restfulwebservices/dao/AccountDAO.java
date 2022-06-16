package com.in28mintues.restfulwebservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28mintues.restfulwebservices.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{

}
