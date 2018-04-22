package by.htp.librarywithsql.dao.impl;

import static by.htp.librarywithsql.dao.util.DBConnectionHelper.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.librarywithsql.bean.Book;
import by.htp.librarywithsql.dao.BookDao;

public class BookDaoDBImpl implements BookDao {
	private static final String SQL_SELECT_BOOKS = "select * from book";
	private static final String SQL_SELECT_BOOK_BY_ID = "select * from book where id = ";
	private static final String SQL_INSERT_BOOK = "insert into book (Title, Author, Brief, Publish_Year) values (?,?,?,?)";
	private static final String SQL_UPDATE_BOOK_BY_ID = "update book set title = ?, author = ?, brief = ?, publish_year = ? where id = ?";
	private static final String SQL_DELETE_BOOK_BY_ID = "delete from book where id = ?";
	private static final String SQL_LIBRARY_REPORT_1 = "select employee.Name, count(employee_book.Book_Id) as book_count from employee_book join employee on employee_book.Employee_Id = employee.Id group by employee_book.Employee_Id having book_count > 2 order by book_count";
	private static final String SQL_LIBRARY_REPORT_2 = "select employee.Name, employee.Date_of_Birth, count(employee_book.book_id) as book_count from employee_book join employee on employee_book.employee_id = employee.Id group by employee_book.employee_id having book_count <= 5 order by employee.Date_of_Birth, book_count desc";
	
	public int create(Book entity) {
		Connection connection = connect();
		int result = 0;
		try {
			PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_BOOK);
			pstmt.setString(1, entity.getTitle());
			pstmt.setString(2, entity.getAuthor());
			pstmt.setString(3, entity.getDescription());
			pstmt.setInt(4, entity.getYear());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);
		return result;
	}

	public Book read(int id) {
		Connection connection = connect();
		Book book = null;
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOK_BY_ID + id);
			while(rs.next()){			
					book = new Book();
					book.setId(rs.getInt("id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("brief"));
					book.setYear(rs.getInt("publish_year"));					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);
		return book;
	}

	public List<Book> readAll() {
		Connection connection = connect();
		List<Book> books = new ArrayList<Book>();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("brief"));
				book.setYear(rs.getInt("publish_year"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);
		return books;
	}

	private void disconnect(Connection connection) {
		// TODO Auto-generated method stub

	}

	public int update(Book entity, int id) {
		Connection connection = connect();
		int result = 0;
		try {
			PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_BOOK_BY_ID);
			pstmt.setString(1, entity.getTitle());
			pstmt.setString(2, entity.getAuthor());
			pstmt.setString(3, entity.getDescription());
			pstmt.setInt(4, entity.getYear());
			pstmt.setInt(5, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);
		return result;
	}

	public int delete(int id) {
		Connection connection = connect();
		int result = 0;
		try {
			PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_BOOK_BY_ID);			
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);
		return result;
	}

	/*View report on the employees who have read more than 2 book

	The report should contain the following information:
	employee name:  number of books
	The data in the report should be sorted out by the number of books upwards. */
	
	public void report1() {		
		Connection connection = connect();		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_LIBRARY_REPORT_1);
			System.out.println("Employees who have read more than 2 book:");
			while (rs.next()) {
				String employee_name = rs.getString("name");
				int book_count = rs.getInt("book_count");
				System.out.println("Employee = " + employee_name + ", number of books = " + book_count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);		
	}

	
	/*View report on the employees who have read less than or equal to 5 books

	The report should contain the following information:
	employee name, employee date of birth:  number of books
	The data in the report should be sorted out by the employee date of birth and the number of books
	(from the oldest to the youngest employees, and from the largest to the smallest number of books read, i.e. downward). */
	
	public void report2() {		
		Connection connection = connect();		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_LIBRARY_REPORT_2);
			System.out.println("Employees who have read less than or equal to 5 books:");
			while (rs.next()) {
				String employee_name = rs.getString("name");
				Date employee_dob = rs.getDate("Date_of_Birth");
				int book_count = rs.getInt("book_count");
				System.out.println("Employee = " + employee_name + ", employee DoB = " + employee_dob + ", number of books = " + book_count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connection);			
	}	

}
