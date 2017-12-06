package com.madjava.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCTools
{
	private static DataSource dataSource = null;

	// 数据库连接池应该只被创建一次
	static
	{
		dataSource = new ComboPooledDataSource("helloc3p0");
	}

	public static Connection getConnection() throws Exception
	{
		String driverClass = null;
		String user = null;
		String password = null;
		String url = null;
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		url = properties.getProperty("url");

		DriverManager.registerDriver((Driver) Class.forName(driverClass).newInstance());
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	public static Connection getConnectionC3P0() throws SQLException
	{
		return dataSource.getConnection();
	}

	public static void release(Statement st, Connection conn)
	{
		if (null != st)
		{
			try
			{
				st.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (null != conn)
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void release(ResultSet rs, Statement st, Connection conn)
	{
		if (null != rs)
		{
			try
			{
				rs.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		release(st, conn);
	}

	public static void update(String sql)
	{
		Connection conn = null;
		Statement st = null;
		try
		{
			conn = getConnection();
			st = conn.createStatement();

			st.executeUpdate(sql);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			release(null, st, conn);
		}
	}
	
	public static void update(String sql, Object... args)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		try
		{
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++)
			{
				pst.setObject(i + 1, args[i]);
			}
			pst.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			release(null, pst, conn);
		}
	}
}
