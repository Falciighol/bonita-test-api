package com.bird.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.bird.dao.BookDaoImpl;
import com.bird.idao.IBookDao;
import com.bird.model.Book;

import org.bonitasoft.web.extension.ResourceProvider;
 
public class BookController {
	
	public BookController() {
	}
	
	// Calls DAO to save a Book
	public Boolean save(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		Boolean status = false;
		// Executes & saves the DAO transaction status
		status = dao.save(book, rp);
		return status;
	}
	
	// Calls DAO to update a Book
	public Boolean update(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		Boolean status = false;
		// Executes & saves the DAO transaction status
		status = dao.update(book, rp);
		return status;
	}
	
	// Calls DAO to delete a Book
	public Boolean delete(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		Boolean status = false;
		// Executes & saves the DAO transaction status
		status = dao.delete(book, rp);
		return status;
	}
	
	// Calls DAO to get a specific Book
	public List<Book> getById(Integer id, ResourceProvider rp) throws NamingException{
		List<Book> books = new ArrayList<Book>();
		IBookDao dao = new  BookDaoImpl();
		books = dao.getById(id, rp);
        return books;
	}

	// Calls DAO to get all Books
	public List<Book> getBooks(ResourceProvider rp) throws NamingException{
		List<Book> books = new ArrayList<Book>();
		IBookDao dao = new  BookDaoImpl();
		books = dao.getAll(rp);
        return books;
	}
}