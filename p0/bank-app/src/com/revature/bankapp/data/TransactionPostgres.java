package com.revature.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bankapp.ds.ArrayList;
import com.revature.bankapp.ds.List;
import com.revature.bankapp.models.BankAccount;
import com.revature.bankapp.models.Transaction;
import com.revature.bankapp.utils.ConnectionUtil;

public class TransactionPostgres implements TransactionDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Transaction create(Transaction transaction) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into transactions "
					+ "(id, sender_id, receiver_id, amount, transaction_date) "
					+ "values (default, ?, ?, ?, CURRENT_DATE)";
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setInt(1, transaction.getSenderId());
			stmt.setInt(2, transaction.getReceiverId());
			stmt.setDouble(3, transaction.getTransactionAmount());

			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				transaction.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findById(BankAccount userAccount) {
		List<Transaction> transactions = new ArrayList<>();
		int id = userAccount.getId();	
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * "
					+ "from transactions "
					+ "where sender_id=? or receiver_id=?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				int transactionId = resultSet.getInt("id");
				int senderId = resultSet.getInt("sender_id");
				int receiverId = resultSet.getInt("receiver_id");
				double amount = resultSet.getDouble("amount");
				String date = resultSet.getString("transaction_date");
				
				Transaction transaction = new Transaction(transactionId, senderId, receiverId, amount, date);
				
				transactions.add(transaction);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

}
