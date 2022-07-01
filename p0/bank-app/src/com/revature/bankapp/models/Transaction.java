package com.revature.bankapp.models;

import java.util.Objects;

public class Transaction {
	private int id;
	private int senderId;
	private int receiverId;
	private double transactionAmount;
	private String transactionDate;
	
	public Transaction(int senderId, int receiverId, double transactionAmount) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.transactionAmount = transactionAmount;
	}
	public Transaction(int id, int senderId, int receiverId, double transactionAmount, String transactionDate) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int transactionId) {
		this.id = transactionId;
	}
	
	public int getSenderId() {
		return senderId;
	}
	
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	
	public int getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(receiverId, senderId, transactionAmount, transactionDate, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return receiverId == other.receiverId && senderId == other.senderId
				&& transactionAmount == other.transactionAmount && transactionDate == other.transactionDate
				&& id == other.id;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId
				+ ", transactionAmount=" + transactionAmount + ", transactionDate=" + transactionDate + "]";
	}
}
