create table person (
	id serial primary key,
	username varchar(30) unique not null,
	passwd varchar(30) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

create table account_type (
	id serial primary key,
	account_type_name varchar(30)
);

create table bank_account (
	id serial primary key,
	user_id integer references person,
	account_type_id integer references account_type,
	balance decimal(10,2)
);

create table transactions (
	id serial primary key,
	sender_id integer references bank_account,
	receiver_id integer references bank_account,
	amount decimal(10,2),
	transaction_date date not null
);



