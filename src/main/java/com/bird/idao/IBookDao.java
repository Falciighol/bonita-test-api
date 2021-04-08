package com.bird.idao;

import java.util.List;

import javax.naming.NamingException;

import com.bird.model.Book;

import org.bonitasoft.web.extension.ResourceProvider;

public interface IBookDao {
	public boolean save(Book book, ResourceProvider rp) throws NamingException;
	public List<Book> getAll(ResourceProvider rp) throws NamingException;
	public boolean update(Book book, ResourceProvider rp) throws NamingException;
	public boolean delete(Book book, ResourceProvider rp) throws NamingException;
}
