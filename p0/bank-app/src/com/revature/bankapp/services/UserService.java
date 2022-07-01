package com.revature.bankapp.services;

import com.revature.bankapp.ds.List;
import com.revature.bankapp.exceptions.UsernameAlreadyExistsException;
import com.revature.bankapp.models.BankAccount;

public interface UserService {
	public BankAccount registerAccount (BankAccount currentUser) throws UsernameAlreadyExistsException;
	public BankAccount logIn (String username, String password);
	public List<BankAccount> viewAllAccounts();
}
