package com.revature.bankapp.data;


import com.revature.bankapp.ds.List;


// object designed to load and save objects to a file
public interface DataAccessObject<T> {
	/**
	 * Saves the specified object as a new object in 
	 * the data source and returns the object. The returned 
	 * object may be slightly different, e.g. having an ID assigned by 
	 * the data source.
	 * 
	 * @param t the object to be added to the data source
	 * @return the object that was added or null if the object was unable to be added
	 */
	public T create(T t);
	
	/**
	 * Retrieves the object from the data source that matches 
	 * the specified identifier (i.e. ID, primary key, etc.).
	 * 
	 * @param id the identifier of the object to be retrieved
	 * @return the retrieved object or null if no matching object was found
	 */
	public T findById(int id);
	
	/**
	 * Retrieves all of the objects from the data source.
	 * 
	 * @return a List of the retrieved objects
	 */
	public List<T> findAll();
	
	/**
	 * Updates the object in the data source by matching the identifier 
	 * and changing any different values. For example, if the object passed in 
	 * has an identifier of 1, the object with that identifier in the data source 
	 * will be updated to match the values of the object passed in.
	 * 
	 * @param t the updated object to be saved in the data source
	 */
	public void update(T t);
	
	/**
	 * Deletes the object in the data source with the matching identifier.
	 * 
	 * @param t the object to be deleted from the data source
	 */
	public void delete(T t);
}

