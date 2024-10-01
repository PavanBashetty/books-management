package com.jdbc.java.bookProject.service;

import java.util.List;

import com.jdbc.java.bookProject.Book;
import com.jdbc.java.bookProject.repository.BookRepoistory;
import com.jdbc.java.bookProject.repository.PersistenceLayerException;

public class BookService {
	
	private BookRepoistory bookRepoistory = new BookRepoistory();
	
	public Book getBook(Integer id) throws ServiceLayerException {
		try {
			return bookRepoistory.findById(id);
		} catch (PersistenceLayerException e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public List<Book> getAllBooks() throws ServiceLayerException {
		try {
			return bookRepoistory.findAll();
		} catch (PersistenceLayerException e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public void createBook(Book book) throws ServiceLayerException {
		try {
			bookRepoistory.saveOrUpdate(book);
		} catch (PersistenceLayerException e) {
			throw new ServiceLayerException(e);
		}
	}
	
	public void updateBook(Book book) throws ServiceLayerException {
		try {
			bookRepoistory.saveOrUpdate(book);
		} catch (PersistenceLayerException e) {
			throw new ServiceLayerException(e);
		}
	}

	

	//purchaseBook(...)
	// 	update stock
	// 	add sales entry
	// 	mailService.sendNotification(...)
}
