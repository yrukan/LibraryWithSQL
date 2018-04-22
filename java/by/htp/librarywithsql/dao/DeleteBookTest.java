package by.htp.librarywithsql.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.librarywithsql.bean.Book;
import by.htp.librarywithsql.dao.impl.BookDaoDBImpl;
import by.htp.librarywithsql.dao.util.DBConnectionHelper;

public class DeleteBookTest {

	private Connection connection;
	private BookDao dao;	
	private int id;

	@BeforeClass
	public void initDefaultConnection() {
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass");
	}

	@BeforeMethod
	public void getExpectedList() throws SQLException {		
		System.out.println("BeforeMethod");
		id = 22;	
		System.out.println("BeforeMethod: expected book was received");		
	}

	@BeforeMethod
	public void initDao() {
		dao = new BookDaoDBImpl();
	}

	@Test(description = "Check that book is deleted - delete() method")
	public void testDeleteBook() throws SQLException {		
		Assert.assertEquals(1, dao.delete(id), "The book is not deleted");		
	}

	@AfterClass
	public void closeDBConnection() {
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: disconnected");
	}

	@AfterMethod
	public void cleanDBConnection() {
		System.out.println("AfterMethod");
	}

}
