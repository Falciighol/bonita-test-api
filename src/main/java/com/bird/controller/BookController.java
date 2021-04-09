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
	public void save(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		dao.save(book, rp);
	}
	
	// Calls DAO to update a Book
	public void update(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		dao.update(book, rp);
	}
	
	// Calls DAO to delete a Book
	public void delete(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		dao.delete(book, rp);
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