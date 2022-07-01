package com.revature.bankapp.ds;

public interface List<T> {
	public void add (T obj);
	public T get (int index);
	public T delete (int index);
	public int indexOf (T obj);
	public default List<T> emptyList() {
		return null;
	}
}
