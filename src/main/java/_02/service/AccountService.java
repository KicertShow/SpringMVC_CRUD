package _02.service;

import java.util.List;

import _02.Account;

public interface AccountService {
	void save(Account acc);

	Account findByPrimaryKey(String accountNo, String accountType);

	void update(Account acc);

	void delete(Account acc);

	List<Account> findAll();
}
