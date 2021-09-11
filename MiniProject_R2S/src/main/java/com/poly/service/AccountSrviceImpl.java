package com.poly.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.DAO.AccountDAO;
import com.poly.entity.Account;


@Service
public class AccountSrviceImpl implements AccountService{
	@Autowired
	AccountDAO accountDAO;

	@Override
	public Account save(Account entity) {
		return accountDAO.save(entity);
	}


	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountDAO.findAll(pageable);
	}

	@Override
	public List<Account> findAll() {
		return (List<Account>)accountDAO.findAll();
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return accountDAO.findAll(sort);
	}

	@Override
	public List<Account> findAllById(List<String> ids) {
		return (List<Account>)accountDAO.findAllById(ids);
	}

	@Override
	public Optional<Account> findById(String id) {
		return accountDAO.findById(id);
	}

	@Override
	public List<Account> saveAll(List<Account> entities) {
		return (List<Account>)accountDAO.saveAll(entities);
	}

	@Override
	public void flush() {
		accountDAO.flush();
	}


	@Override
	public boolean existsById(String id) {
		return accountDAO.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		accountDAO.deleteInBatch(entities);
	}


	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		accountDAO.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return accountDAO.count();
	}

	@Override
	public void deleteById(String id) {
		accountDAO.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Account entity) {
		accountDAO.delete(entity);
	}

	@Override
	public void deleteAllById(List<String> ids) {
		accountDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		accountDAO.deleteAllInBatch();
	}

	@Override
	public Account getOne(String id) {
		return accountDAO.getOne(id);
	}

	@Override
	public void deleteAll(List<Account> entities) {
		accountDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		accountDAO.deleteAll();
	}

	@Override
	public Account getById(String id) {
		return accountDAO.getById(id);
	}


	
	
}
