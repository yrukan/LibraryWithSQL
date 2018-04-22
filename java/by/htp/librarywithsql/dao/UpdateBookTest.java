package by.htp.librarywithsql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.librarywithsql.bean.Book;
import by.htp.librarywithsql.dao.impl.BookDaoDBImpl;
import by.htp.librarywithsql.dao.util.DBConnectionHelper;

public class UpdateBookTest {

	private Connection connection;
	private BookDao dao;
	private Book expectedBook;
	private int id;

	@BeforeClass
	public void initDefaultConnection() {
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass");

	}

	@BeforeMethod
	public void getExpectedList() throws SQLException {		
		System.out.println("BeforeMethod");
		expectedBook = new Book("Книга update", "Автор update", "Описание книги update", 2019);
		id = 17;
		System.out.println("BeforeMethod: expected book was received");		
	}

	@BeforeMethod
	public void initDao() {
		dao = new BookDaoDBImpl();
	}

	@Test(description = "Check that book is updated - update() method")
	public void testUpdateBook() throws SQLException {		
		Assert.assertEquals(1, dao.update(expectedBook, id), "the book is not updated");		
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
