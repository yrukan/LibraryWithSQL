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

public class ReadBookTest {

	private Connection connection;
	private Book expectedBook;
	private BookDao dao;
	private int id = 1;

	@BeforeClass
	public void initDefaultConnection() {
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass");

	}

	@BeforeMethod
	public void getExpectedList() throws SQLException {		
		System.out.println("BeforeMethod");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from book where id = " + id);
		
		while (rs.next()) {
			expectedBook = new Book();
			expectedBook.setId(rs.getInt("id"));
			expectedBook.setTitle(rs.getString("title"));
			expectedBook.setAuthor(rs.getString("author"));
			expectedBook.setDescription(rs.getString("brief"));
			expectedBook.setYear(rs.getInt("publish_year"));						
		}
		System.out.println("BeforeMethod: expected book was found by id");		
	}

	@BeforeMethod
	public void initDao() {
		dao = new BookDaoDBImpl();
	}

	@Test(description = "Check received book - read() method")
	public void testReceivedBook() throws SQLException {
		Book actualBook = dao.read(id);
		actualBook.equals(expectedBook);
		Assert.assertTrue(actualBook.equals(expectedBook), "The received book is not correct");		
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
