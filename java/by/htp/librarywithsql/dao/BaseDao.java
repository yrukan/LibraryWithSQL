package by.htp.librarywithsql.dao;

import java.sql.SQLException;
import java.util.List;

import by.htp.librarywithsql.bean.Entity;

public interface BaseDao<T extends Entity>{
	
	public int create(T entity);
	public T read(int id);
	public List<T> readAll() throws SQLException;
	public void report1();
	public void report2();	
	public int update(T entity, int id);
	public int delete(int id);

}
