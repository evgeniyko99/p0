package com.revature.bankapp.data;

import com.revature.bankapp.models.Person;

public interface PersonDAO extends DataAccessObject<Person> {
	public Person findByUsername(String username);
}
