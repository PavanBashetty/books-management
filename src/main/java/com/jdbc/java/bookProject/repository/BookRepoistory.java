package com.jdbc.java.bookProject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.java.bookProject.Book;

public class BookRepoistory {

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	
	public List<Book> findByTitle(String title)throws SQLException {
		Connection conn = null;
		try {
			conn = connectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE title LIKE ?");
			ps.setString(1, "%"+title+"%");
			ResultSet rs = ps.executeQuery();
			List<Book> result = new ArrayList<Book>();
			while(rs.next()) {
				Book book = mapToBook(rs);
				result.add(book);
			}
			return result;
		}finally {
			if(conn != null) {
				conn.close();
			}
		}
	}

	private Book mapToBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setIsbn(rs.getString("isbn"));
		book.setPublishedDate(rs.getDate("publishedDate"));
		book.setPrice(rs.getDouble("price"));
		return book;
	}
}
