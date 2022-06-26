package _02.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _02.Account;
import _02.AccountId;
import _02.dao.AccountDao;

@Repository
public class AccountDaoImpl implements AccountDao {

	SessionFactory  factory;
	
	@Autowired
	public AccountDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void save(Account acc) {
		Session session = factory.getCurrentSession();
		System.out.println("In DAO, account=" + acc);
		session.save(acc);
	}

	@Override
	public Account findByPrimaryKey(String accountNo, String accountType) {
		Session session = factory.getCurrentSession();
		Account acc = session.get(Account.class, 
						new AccountId(accountNo, accountType));
		return acc;
	}

	@Override
	public void update(Account acc) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(acc);
	}

	@Override
	public void delete(Account acc) {
		Session session = factory.getCurrentSession();
		session.delete(acc);
	}

	@Override
	public List<Account> findAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Account";
		return session.createQuery(hql, Account.class)
				      .getResultList();
	}

}
