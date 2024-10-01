package com.jdbc.java.bookProject;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.java.bookProject.repository.BookRepoistory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        
    	BookRepoistory respository = new BookRepoistory();
    	List<Book> books = respository.findByTitle("Java");
    	for(Book b: books) {
    		System.out.println(b);
    	}
    }
}
