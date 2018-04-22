package by.htp.librarywithsql;

import java.sql.SQLException;
import java.util.List;

import by.htp.librarywithsql.bean.Book;
import by.htp.librarywithsql.dao.BookDao;
import by.htp.librarywithsql.dao.impl.BookDaoDBImpl;

public class MainApp {
	public static void main(String[] args) throws SQLException {
		
		BookDao dao = new BookDaoDBImpl();
		Book new_book = new Book("Книга new", "Автор new", "Описание книги new", 2018);
		int id = 20;
		List<Book> books = null;
		
		//dao.create(new_book);
		
		//System.out.println(dao.read(id).toString());
		
		//dao.update(new_book, id);
		
		//dao.delete(id);

		books = dao.readAll();		
		for (Book book : books) {
			System.out.println(book.toString());
		}
		
		//dao.report1();
		//dao.report2();
	}
}
