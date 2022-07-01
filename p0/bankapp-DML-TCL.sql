insert into account_type (account_type_name) values ('savings'), ('checking');

insert into status_type (status_type_name) values ('failed'), ('complete');

insert into person (username, passwd, first_name, last_name) values ('owhitebread0', '7EYGFCkMbgC', 'Ode', 'Whitebread');
insert into person (username, passwd, first_name, last_name) values ('mgrimsdith1', 'pXR69q', 'Mayer', 'Grimsdith');
insert into person (username, passwd, first_name, last_name) values ('vorpyne2', 'Rl5x4x9UPL', 'Van', 'Orpyne');
insert into person (username, passwd, first_name, last_name) values ('gmccollum3', 'F6Hdg6atGEAn', 'Georgia', 'McCollum');
insert into person (username, passwd, first_name, last_name) values ('istather4', 'lCRuAS', 'Issiah', 'Stather');
insert into person (username, passwd, first_name, last_name) values ('rmcaneny5', 'VSeHQi2o2GY', 'Rhoda', 'McAneny');
insert into person (username, passwd, first_name, last_name) values ('hcrucetti6', '1cV0Od6sId3K', 'Hermon', 'Crucetti');
insert into person (username, passwd, first_name, last_name) values ('mcostall7', '4wSK33qF', 'Mattheus', 'Costall');
insert into person (username, passwd, first_name, last_name) values ('lmcfetrich8', 'mod0mN', 'Lorenza', 'McFetrich');
insert into person (username, passwd, first_name, last_name) values ('lmorrissey9', '0onzAn', 'Lynnell', 'Morrissey');
insert into person (username, passwd, first_name, last_name) values ('cflavertya', 'hx2hh0', 'Cornelius', 'Flaverty');
insert into person (username, passwd, first_name, last_name) values ('wlukeschb', '2tLnWYv', 'Web', 'Lukesch');
insert into person (username, passwd, first_name, last_name) values ('todwyerc', 'ADp1bs5gj', 'Tabor', 'O''Dwyer');
insert into person (username, passwd, first_name, last_name) values ('bpogued', 'DdRShY', 'Barbe', 'Pogue');
insert into person (username, passwd, first_name, last_name) values ('cahline', 'pI0mgFRlXk4Y', 'Casey', 'Ahlin');
insert into person (username, passwd, first_name, last_name) values ('rhaskerf', 'tJENGVzkrf', 'Rosina', 'Hasker');
insert into person (username, passwd, first_name, last_name) values ('elezemoreg', 'gG2ozLxqpLF', 'Evangeline', 'Lezemore');
insert into person (username, passwd, first_name, last_name) values ('ngartonh', 'JrA8Zc5D6L5', 'Nial', 'Garton');
insert into person (username, passwd, first_name, last_name) values ('gfaini', 'W3cQkwa', 'Gaspard', 'Fain');
insert into person (username, passwd, first_name, last_name) values ('cslevinj', 'R6zmnoE94xj', 'Celeste', 'Slevin');

insert into bank_account (user_id, account_type_id, balance) values (10, 1, 73143.18);
insert into bank_account (user_id, account_type_id, balance) values (18, 2, 44793.61);
insert into bank_account (user_id, account_type_id, balance) values (4, 2, 22235.1);
insert into bank_account (user_id, account_type_id, balance) values (19, 2, 29480.59);
insert into bank_account (user_id, account_type_id, balance) values (10, 2, 57632.53);
insert into bank_account (user_id, account_type_id, balance) values (19, 2, 30568.31);
insert into bank_account (user_id, account_type_id, balance) values (20, 2, 75518.27);
insert into bank_account (user_id, account_type_id, balance) values (3, 1, 91573.56);
insert into bank_account (user_id, account_type_id, balance) values (19, 1, 87046.04);
insert into bank_account (user_id, account_type_id, balance) values (1, 2, 60417.41);
insert into bank_account (user_id, account_type_id, balance) values (7, 1, 84764.41);
insert into bank_account (user_id, account_type_id, balance) values (15, 1, 63624.83);
insert into bank_account (user_id, account_type_id, balance) values (15, 1, 61784.34);
insert into bank_account (user_id, account_type_id, balance) values (12, 2, 7831.61);
insert into bank_account (user_id, account_type_id, balance) values (15, 2, 34008.42);
insert into bank_account (user_id, account_type_id, balance) values (1, 2, 90344.77);
insert into bank_account (user_id, account_type_id, balance) values (5, 1, 47637.9);
insert into bank_account (user_id, account_type_id, balance) values (15, 2, 35211.88);
insert into bank_account (user_id, account_type_id, balance) values (13, 2, 50128.51);
insert into bank_account (user_id, account_type_id, balance) values (14, 2, 6662.54);
insert into bank_account (user_id, account_type_id, balance) values (12, 1, 2271.64);
insert into bank_account (user_id, account_type_id, balance) values (12, 2, 52419.93);
insert into bank_account (user_id, account_type_id, balance) values (13, 2, 99903.04);
insert into bank_account (user_id, account_type_id, balance) values (18, 2, 54776.2);
insert into bank_account (user_id, account_type_id, balance) values (17, 2, 16219.99);
insert into bank_account (user_id, account_type_id, balance) values (6, 2, 100.00);

delete from bank_account where user_id = 21;

select * from bank_account;