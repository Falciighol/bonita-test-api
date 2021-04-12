package com.bird.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		PreparedStatement stmt = null;
		Connection conn = null;
		Integer rowsInserted = 0;
		
		String sql="INSERT INTO book VALUES (?, ?)";
		
		try {			
			conn = Connect.connect(rp);
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, book.getId());
			stmt.setString(2, book.getName());

			rowsInserted = stmt.executeUpdate();

			if (rowsInserted > 0)
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
		
		String sql = "SELECT * FROM book ORDER BY name;";
		
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Book book = new Book();
		
		String sql = "SELECT * FROM book WHERE id = ? ORDER BY name;";
		
		List<Book> books= new ArrayList<Book>();
		
		try {			
			conn = Connect.connect(rp);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
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
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean updated = false;
		Integer rowsUpdated = 0;

		String sql="UPDATE book SET name=? WHERE id=?;";

		try {
			conn = Connect.connect(rp);
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, book.getName());
			stmt.setInt(2, book.getId());

			rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated > 0)
				updated = true;

			stmt.close();
			conn.close();
		} catch (SQLException e) {
            LOGGER.error("Error while updating DB data: " + e.getMessage());
			e.printStackTrace();
		}		
		return updated;
	}
 
	@Override
	public boolean delete(Book book, ResourceProvider rp) throws NamingException {
		boolean deleted = false;
		Connection connect = null;
		PreparedStatement stmt = null;
		Integer rowsDeleted = 0;
				
		String sql = "DELETE FROM book WHERE ID=" + book.getId();

		try {
			connect = Connect.connect(rp);
			stmt = connect.prepareStatement(sql);
			rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted > 0)
				deleted = true;

		} catch (SQLException e) {
			LOGGER.error("Error while deleting data from DB: " + e.getMessage());
			e.printStackTrace();
		}		
		return deleted;
	}
 
}