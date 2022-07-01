package com.revature.bankapp.data;

import com.revature.bankapp.ds.List;
import com.revature.bankapp.models.BankAccount;
import com.revature.bankapp.models.Person;

public interface AccountDAO extends DataAccessObject<BankAccount> {
	public List<BankAccount> findByUser(Person user);
}
