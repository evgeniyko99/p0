package com.revature.bankapp.ds;

public abstract class LinkedList implements List{
	public Object currentNode;
	public Object headNode;
	
	public abstract Object nextNode();
	
	public abstract void add (Object obj, int index);
	
	public void add (Object obj) {
		// TODO
	}
}
