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
	
	//llama al DAO para guardar un book
	public void save(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		dao.save(book, rp);
	}
	
	//llama al DAO para actualizar un book
	public void update(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		dao.update(book, rp);
	}
	
	//llama al DAO para eliminar un book
	public void delete(Book book, ResourceProvider rp) throws NamingException {
		IBookDao dao = new  BookDaoImpl();
		dao.delete(book, rp);
	}
	
	//llama al DAO para obtener todos los books y luego los muestra en la vista
	public List<Book> getBooks(ResourceProvider rp) throws NamingException{
		List<Book> books = new ArrayList<Book>();
		IBookDao dao = new  BookDaoImpl();
		books = dao.getAll(rp);
        return books;
	}
}