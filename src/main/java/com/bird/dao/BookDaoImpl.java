package com.bird.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.bird.connection.Connect;
import com.bird.idao.IBookDao;
import com.bird.model.Book;

import org.bonitasoft.web.extension.ResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class BookDaoImpl implements IBookDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);
	
	@Override
	public boolean save(Book book, ResourceProvider rp) throws NamingException {
		boolean added = false;
		
		Statement stmt = null;
		Connection conn = null;
		
		String sql="INSERT INTO book values (" + book.getId() + ", '" + book.getName() + "')";
		
		try {			
			conn = Connect.connect(rp);
			stmt = conn.createStatement();
			stmt.execute(sql);
			added = true;
			stmt.close();
			conn.close();
		} catch (SQLException e) {
            LOGGER.error("Error while adding data to DB" + e.getMessage());
			e.printStackTrace();
		}
		return added;
	}
 
	@Override
	public List<Book> getAll(ResourceProvider rp) throws NamingException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM book ORDER BY id";
		
		List<Book> books= new ArrayList<Book>();
		
		try {			
			conn = Connect.connect(rp);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt(1));
				b.setName(rs.getString(2));
				books.add(b);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("Error while getting data from DB: " + e.getMessage());
			e.printStackTrace();
		}
		
		return books;
	}

	@Override
	public List<Book> getById(Integer id, ResourceProvider rp) throws NamingException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Book book = new Book();
		
		String sql = "SELECT * FROM book WHERE id = " + id + " ORDER BY id";
		
		List<Book> books= new ArrayList<Book>();
		
		try {			
			conn = Connect.connect(rp);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				book.setId(rs.getInt(1));
				book.setName(rs.getString(2));
				books.add(book);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("Error while getting data from DB: " + e.getMessage());
			e.printStackTrace();
		}
		
		return books;
	}
 
	@Override
	public boolean update(Book book, ResourceProvider rp) throws NamingException {
		Connection connect= null;
		Statement stmt= null;
		
		boolean updated=false;
				
		String sql="UPDATE book SET name='" + book.getName()+"' WHERE ID=" + book.getId();
		try {
			connect = Connect.connect(rp);
			stmt = connect.createStatement();
			stmt.execute(sql);
			updated = true;
		} catch (SQLException e) {
            LOGGER.error("Error while updating DB data: " + e.getMessage());
			e.printStackTrace();
		}		
		return updated;
	}
 
	@Override
	public boolean delete(Book book, ResourceProvider rp) throws NamingException {
		Connection connect = null;
		Statement stmt = null;
		
		boolean deleted = false;
				
		String sql = "DELETE FROM book WHERE ID=" + book.getId();
		try {
			connect = Connect.connect(rp);
			stmt = connect.createStatement();
			stmt.execute(sql);
			deleted = true;
		} catch (SQLException e) {
			LOGGER.error("Error while deleting data from DB: " + e.getMessage());
			e.printStackTrace();
		}		
		return deleted;
	}
 
}