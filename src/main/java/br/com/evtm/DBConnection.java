package br.com.evtm;

import java.sql.*;

public class DBConnection {

	public static void main(String args[]) throws SQLException, ClassNotFoundException{

        System.out.println("Loading/Registering driver");
        Class.forName("com.mysql.jdbc.Driver");
         

        System.out.println("Connecting to database");
        String url = "jdbc:mysql://localhost/";
        String userName = "root";
        String pasword = "";
        Connection conn = DriverManager.getConnection(url, userName, pasword);
        
        String query = "CREATE TABLE AVALIACAO_SUB_TIPO (id int, name varchar(30), PRIMARY KEY(id))";
        Statement stmt = conn.createStatement();
        stmt.execute(query);     
        
        /* Insert data to employee table */
        query = "INSERT INTO employee values(1, \"Krishna\")";
        stmt.execute(query);
        query = "INSERT INTO employee values(2, \"Arjun\")";
        stmt.execute(query);
        
        query = "SELECT * FROM employee";
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id +" " + name);
        }
        rs.close();
        conn.close();
        conn.close();
    }
}
