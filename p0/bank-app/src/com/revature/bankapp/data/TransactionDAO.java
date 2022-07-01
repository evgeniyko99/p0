package com.revature.bankapp.data;

import com.revature.bankapp.ds.List;
import com.revature.bankapp.models.BankAccount;
import com.revature.bankapp.models.Transaction;

public interface TransactionDAO extends DataAccessObject<Transaction> {
	public List<Transaction> findById(BankAccount t);
}
