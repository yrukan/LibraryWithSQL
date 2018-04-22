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

public class CreateBookTest {

	private Connection connection;
	private BookDao dao;
	private Book expectedBook;

	@BeforeClass
	public void initDefaultConnection() {
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass");

	}

	@BeforeMethod
	public void getExpectedBook() throws SQLException {		
		System.out.println("BeforeMethod");
		expectedBook = new Book("Книга new", "Автор new", "Описание книги new", 2018);		
		System.out.println("BeforeMethod: expected book was received");		
	}

	@BeforeMethod
	public void initDao() {
		dao = new BookDaoDBImpl();
	}

	@Test(description = "Check that book is created - create() method")
	public void testCreateBook() throws SQLException {		
		Assert.assertEquals(1, dao.create(expectedBook), "the book is not inserted");		
	}

	@AfterClass
	public void closeDBConnection() {
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: disconnected");
	}

	@AfterMethod
	public void cleanDBConnection() {
		expectedBook = null;
		System.out.println("AfterMethod");
	}

}
