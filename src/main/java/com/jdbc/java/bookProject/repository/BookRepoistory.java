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
	
	public List<Book> findByTitle(String title)throws PersistenceLayerException {
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
		}catch(SQLException e) {
			throw new PersistenceLayerException(e);
		}
		finally {
			close(conn);
		}
	}
	
	public Book findById(Integer id) throws PersistenceLayerException{
		Connection conn = null;
		try {
			conn = connectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Book book = mapToBook(rs);
				return book;
			}
			return null;
		}catch(SQLException e) {
			throw new PersistenceLayerException(e);
		}finally {
			close(conn);
		}
	}
	
	public List<Book> findAll() throws PersistenceLayerException{
		Connection conn = null;
		try {
			conn = connectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");
			ResultSet rs = ps.executeQuery();
			List<Book> result = new ArrayList<Book>();
			while(rs.next()) {
				Book book = mapToBook(rs);
				result.add(book);
			}
			return result;
		}
		catch(SQLException e) {
			throw new PersistenceLayerException(e);
		}finally {
			close(conn);
		}
	}

	public void saveOrUpdate(Book book) throws PersistenceLayerException{
		Connection conn = null;
		try {
			conn = connectionFactory.getConnection();
			if(book.getId() == null) {
				PreparedStatement psInsert = conn.prepareStatement("INSERT INTO books(title,author,isbn,publishedDate,price) VALUES(?,?,?,?,?)");
				psInsert.setString(1, book.getTitle());
				psInsert.setString(2, book.getAuthor());
				psInsert.setString(3, book.getIsbn());
				psInsert.setDate(4, book.getPublishedDate());
				psInsert.setDouble(5, book.getPrice());
				psInsert.executeUpdate();
			}else {
				PreparedStatement psUpdate = conn.prepareStatement("UPDATE books SET title=?, author=?, isbn=?, publishedDate=?, price=? WHERE id=?");
				psUpdate.setString(1, book.getTitle());
				psUpdate.setString(2, book.getAuthor());
				psUpdate.setString(3, book.getIsbn());
				psUpdate.setDate(4, book.getPublishedDate());
				psUpdate.setDouble(5, book.getPrice());
				psUpdate.setInt(6, book.getId());
				psUpdate.executeUpdate();
			}
		}
		catch(SQLException e) {
			throw new PersistenceLayerException(e);
		}finally {
			close(conn);
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
	
	private void close(Connection conn) throws PersistenceLayerException  {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



















