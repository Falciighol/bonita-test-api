package com.bird.connection;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bonitasoft.web.extension.ResourceProvider;

public class Connect
{

    private static final Logger LOGGER = LoggerFactory.getLogger(Connect.class);
    
    public static Connection connect(ResourceProvider pageResourceProvider) throws NamingException
    {
        Connection conn = null;
        Context ctx = new InitialContext();

        try {
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/demoDS");
            // DataSource dataSource = (DataSource) ctx.lookup(getDSName());
            conn = (Connection) dataSource.getConnection();
        } catch (Exception e) {
            LOGGER.error("Error while connecting to DB");
            LOGGER.error("Message: " + e.getMessage());
        }
        return conn;
	}

    public static Connection connect(ResourceProvider pageResourceProvider, String DSName) throws NamingException
    {
        Connection conn = null;
        Context ctx = new InitialContext();

        try {
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/" + DSName);
            // DataSource dataSource = (DataSource) ctx.lookup(getDSName());
            conn = (Connection) dataSource.getConnection();
        } catch (Exception e) {
            LOGGER.error("Error while connecting to DB");
            LOGGER.error("Message: " + e.getMessage());
        }
        return conn;
	}

}
