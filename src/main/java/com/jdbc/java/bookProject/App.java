package com.jdbc.java.bookProject;

import java.util.List;

import com.jdbc.java.bookProject.repository.BookRepoistory;
import com.jdbc.java.bookProject.repository.PersistenceLayerException;


public class App {
    public static void main(String[] args) throws PersistenceLayerException {
    	
    	BookRepoistory repository = new BookRepoistory();
    	
    	Book newBook = new Book();
    	newBook.setId(2);
    	newBook.setTitle("Head First Java");
    	newBook.setAuthor("Kathy Sierra");
    	newBook.setIsbn("isbn123");
    	newBook.setPublishedDate(new java.sql.Date(new java.util.Date().getTime()));
    	newBook.setPrice(24.33);
    	repository.saveOrUpdate(newBook);
        
    	List<Book> books = repository.findByTitle("Java");
    	for(Book b: books) {
    		System.out.println(b);
    	}
    	
    	Book booksById = repository.findById(1);
    	System.out.println(booksById);
    	
    	List<Book> allBooks = repository.findAll();
    	for(Book b:allBooks) {
    		System.out.println(b);
    	}
  
    }
}
