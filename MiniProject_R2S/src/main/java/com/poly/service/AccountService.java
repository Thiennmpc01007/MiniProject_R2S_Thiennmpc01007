package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.Account;

public interface AccountService {

	Account getById(String id);

	void deleteAll();

	void deleteAll(List<Account> entities);

	Account getOne(String id);

	void deleteAllInBatch();

	void deleteAllById(List<String> ids);

	void delete(Account entity);

	void deleteAllByIdInBatch(Iterable<String> ids);

	void deleteById(String id);

	long count();

	void deleteAllInBatch(Iterable<Account> entities);

	void deleteInBatch(Iterable<Account> entities);

	boolean existsById(String id);

	void flush();

	List<Account> saveAll(List<Account> entities);

	Optional<Account> findById(String id);

	List<Account> findAllById(List<String> ids);

	List<Account> findAll(Sort sort);

	List<Account> findAll();

	Page<Account> findAll(Pageable pageable);

	Account save(Account entity);
	
}
