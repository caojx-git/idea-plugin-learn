package com.caojx.learn.agent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApiTest {

    public static void main(String[] args) throws Exception {

        String URL = "jdbc:mysql://127.0.0.1:3306/yeecode?characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "root";
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql="SELECT * FROM USER WHERE id = ? AND name = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1,1L);
        statement.setString(2,"易哥");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("email"));
        }

    }
}
