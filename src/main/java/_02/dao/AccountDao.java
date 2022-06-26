package _02.dao;

import java.util.List;

import _02.Account;

public interface AccountDao {
	
	void save(Account acc);
	
	Account findByPrimaryKey(String accountNo, String accountType);
	
	void update(Account acc);
	
	void delete(Account acc);
	
	List<Account> findAll();
	
	
}
