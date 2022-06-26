package _02.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _02.Account;
import _02.dao.AccountDao;
import _02.service.AccountService;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

	AccountDao accountDao;
	
	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void save(Account acc) {
		accountDao.save(acc);	
	}

	@Override
	public Account findByPrimaryKey(String accountNo, String accountType) {
		return accountDao.findByPrimaryKey(accountNo, accountType);
	}

	@Override
	public void update(Account acc) {
		accountDao.update(acc);
	}

	@Override
	public void delete(Account acc) {
		accountDao.delete(acc);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

}
