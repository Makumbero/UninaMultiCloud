package Dao;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
public class ConnessioneDB {
Connection conn;

	public void connettiti(){
		        try {
		            String url = "jdbc:postgresql://localhost:5432/postgres";
		            String user = "postgres";
		            String password = "Elefante";
	
		            Connection conn = DriverManager.getConnection(url, user, password);
		            this.conn=conn;
	
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		       
	    	}
	public Connection getConnection() {
		return conn;
	}
}
	