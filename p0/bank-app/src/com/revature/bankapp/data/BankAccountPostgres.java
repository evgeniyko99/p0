package com.revature.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.bankapp.ds.ArrayList;
import com.revature.bankapp.ds.List;
import com.revature.bankapp.models.BankAccount;
import com.revature.bankapp.models.Person;
import com.revature.bankapp.utils.ConnectionUtil;

public class BankAccountPostgres implements AccountDAO{
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public BankAccount create(BankAccount bankAccount) {
		
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bank_account "
					+ "(id, user_id, account_type_id, balance) "
					+ "values (default, ?, ?, ?)";
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setInt(1, bankAccount.getUser().getId());
			stmt.setString(2, bankAccount.getAccountType());
			stmt.setDouble(3, 0.00);

			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				bankAccount.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return bankAccount;
	}

	@Override
	public BankAccount findById(int id) {
		BankAccount bankAccount = null;
		
		try (Connection conn = connUtil.getConnection()){
			String sql = "select bank_account.id, "
					+ "user_id, "
					+ "username, "
					+ "passwd, "
					+ "first_name, "
					+ "last_name, "
					+ "account_type_id, "
					+ "balance "
					+ "from bank_account "
					+ "join person on bank_account.user_id = person.id "
					+ "join account_type on bank_account.account_type_id = account_type.id "
					+ "where bank_account.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  id);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				int bankAccountId = resultSet.getInt("id");
				Person user = new Person( 
						resultSet.getInt("user_id"),
						resultSet.getString("username"),
						resultSet.getString("passwd"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"));
				int accountType = resultSet.getInt("account_type_id");
				double balance = resultSet.getDouble("balance");
				
				bankAccount = new BankAccount(bankAccountId, user, accountType, balance);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return bankAccount;
	}

	@Override
	public List<BankAccount> findAll() {
		List<BankAccount> allAccounts = new ArrayList<>();
		
		try (Connection conn = connUtil.getConnection()){
			String sql = "select bank_account.id, "
					+ "user_id, "
					+ "username, "
					+ "passwd, "
					+ "first_name, "
					+ "last_name, "
					+ "account_type_name, "
					+ "balance "
					+ "from bank_account "
					+ "join person on bank_account.user_id = person.id "
					+ "join account_type on bank_account.account_type_id = account_type.id;";
			
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				int bankAccountId = resultSet.getInt("id");
				int userId = resultSet.getInt("user_id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("passwd");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				int accountType = resultSet.getInt("account_type_id");
				double balance = resultSet.getDouble("balance");
				
				Person person = new Person(username, password, firstName, lastName);
				person.setId(userId);
				BankAccount bankAccount = new BankAccount(bankAccountId, person, accountType, balance);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return allAccounts;
	}

	@Override
	public void update(BankAccount t) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update bank_account "
					+ "set balance = ?"
					+ "where id = ?";
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setDouble(1, t.getBalance());
			stmt.setInt(2, t.getId());
	
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				t.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void delete(BankAccount t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BankAccount> findByUser(Person user) {
		List<BankAccount> allAccounts = new ArrayList<>();
		int id = user.getId();
		
		try (Connection conn = connUtil.getConnection()){
			String sql = "select bank_account.id, "
					+ "user_id, "
					+ "username, "
					+ "passwd, "
					+ "first_name, "
					+ "last_name, "
					+ "account_type_id, "
					+ "balance "
					+ "from bank_account "
					+ "join person on bank_account.user_id = person.id "
					+ "join account_type on bank_account.account_type_id = account_type.id "
					+ "where user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				int bankAccountId = resultSet.getInt("id");
				Person person = new Person(
						resultSet.getInt("user_id"),
						resultSet.getString("username"),
						resultSet.getString("passwd"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name")
						);
				int accountType = resultSet.getInt("account_type_id");
				double balance = resultSet.getDouble("balance");
				
				BankAccount bankAccount = new BankAccount(bankAccountId, person, accountType, balance);
				
				allAccounts.add(bankAccount);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return allAccounts;
	}

}
