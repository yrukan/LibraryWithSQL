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

public class ReadAllTest {

	private Connection connection;
	private List<Book> expectedList;
	private BookDao dao;

	@BeforeClass
	public void initDefaultConnection() {
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass");

	}

	@BeforeMethod
	public void getExpectedList() throws SQLException {		
		System.out.println("BeforeMethod");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from book");

		expectedList = new ArrayList<Book>();
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setDescription(rs.getString("brief"));
			book.setYear(rs.getInt("publish_year"));
			expectedList.add(book);			
		}
		System.out.println("BeforeMethod: expected book list was received");		
	}

	@BeforeMethod
	public void initDao() {
		dao = new BookDaoDBImpl();
	}

	@Test(description = "Check received books count - readAll() method")
	public void testReceivedBooksCount() throws SQLException {
		List<Book> actualList = dao.readAll();
		Assert.assertEquals(actualList.size(), expectedList.size(),
				"the received number of books is not equal");		
	}

	@AfterClass
	public void closeDBConnection() {
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: disconnected");
	}

	@AfterMethod
	public void cleanDBConnection() {
		expectedList = null;
		System.out.println("AfterMethod");
	}

}
