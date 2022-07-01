package com.revature.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bankapp.ds.List;
import com.revature.bankapp.models.Person;
import com.revature.bankapp.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Person create(Person person) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into person "
					+ "(id, username, passwd, first_name, last_name) "
					+ "values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, person.getUsername());
			stmt.setString(2, person.getPassword());
			stmt.setString(3, person.getFirstName());
			stmt.setString(4, person.getLastName());

			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				person.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return person;
	}

	@Override
	public Person findById(int id) {
		Person person = null;
		
		try (Connection conn = connUtil.getConnection()){
			String sql = "select * "
					+ "from person "
					+ "where user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  id);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				int bankAccountId = resultSet.getInt("id");
				int userId = resultSet.getInt("user_id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("passwd");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String accountType = resultSet.getString("account_type_name");
				double balance = resultSet.getDouble("balance");
				
				person = new Person(username, password, firstName, lastName);
				person.setId(userId);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return person;
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person findByUsername(String username) {
		Person person = null;
		
		try (Connection conn = connUtil.getConnection()){
			String sql = "select * "
					+ "from person "
					+ "where username = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,  username);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				int personId = resultSet.getInt("id");
				String password = resultSet.getString("passwd");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				
				person = new Person(username, password, firstName, lastName);
				person.setId(personId);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return person;
	}
	
}
