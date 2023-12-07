/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author pavy
 */
import java.sql.*;

/**
 *
 * @author parwinder
 */
public class dbcon {

    Connection conn = null;
    Statement stmt = null;
    //  String url = "jdbc:mysql://122.160.97.195:3306/";
    // "jdbc:mysql://localhost:3306/";

    String url = "jdbc:mysql://localhost:3306/";
    ResultSet rs = null;
    String userName = "reports";
    String password = "reports@#123";
    //    String userName = "root";
    // String password = "";

    public void getCon(String dbname) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url + dbname + "?autoReconnect=true&allowLoadLocalInfile=true&useSSL=false", userName, password);
        } catch (Exception ex) {
            System.out.println("--exceptionn  getCon-----" + ex);
            ex.printStackTrace();
        }

    }

    public Connection getConnection(String dbname) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url + dbname + "?autoReconnect=true&allowLoadLocalInfile=true&useSSL=false", userName, password);
        } catch (Exception ex) {
            System.out.println("--exceptionn  getCon-----" + ex);
            ex.printStackTrace();
        }

        return conn;
    }

    public Statement getStmt() {
        try {
            if (conn != null) {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            }
        } catch (Exception ex) {
            System.out.println("---exceptionn-getStmt----" + ex);
            ex.printStackTrace();
        }
        return stmt;
    }

    public ResultSet getResult(String sql) {
        try {

            if (conn != null) {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //System.out.println("-- getResult-----" + sql);
                rs = stmt.executeQuery(sql);
            }
        } catch (Exception ex) {
            System.out.println("--exceptionn getResult-----" + ex);
            ex.printStackTrace();
        }
        return rs;
    }

    public int setUpdate(String sql) {
        int i = 0;
        try {

            if (conn != null) {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //System.out.println("-- setUpdate-----" + sql);
                i = stmt.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("---exceptionn setUpdate-----" + ex);
            ex.printStackTrace();
            return -1;
        }
        return i;
    }

    public void closeConection() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (Exception ex) {
            System.out.println("-----closeConnection----" + ex);
            ex.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        dbcon dbc = new dbcon();
        dbc.getCon("mysql");
        System.out.println("-----Main methohod----");

    }
}
