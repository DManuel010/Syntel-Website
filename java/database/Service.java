package database;

import java.sql.Connection;

public abstract class Service {
	protected Connection conn;
	
	// inserts a given object into the appropriate table
	public abstract void insert(Object obj);
	
	// deletes a given object from appropriate table based on primary key
	public abstract void delete(int pk);
}
