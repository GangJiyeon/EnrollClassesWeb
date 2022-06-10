package db;

import java.sql.*;
public class DBtool {
    public static Connection getConnection(){//사용빈도가 높아 static을 붙여서 클래스메서드로 만듦
        Connection conn = null;
        try {
            //jdbc 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");
            //dbms 접속
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/enroll_web?user=root&password=1234");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Statement getStatement(Connection conn){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }
    public static PreparedStatement getPstmt(Connection conn, String sql){
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }
    public static void closeResultSet(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void closeStmt(Statement stmt){
        try{
            if(stmt!=null){
                stmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void closePstmt(PreparedStatement pstmt){
        try{
            if(pstmt!=null){
                pstmt.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void closeConn(Connection conn){
        try{
            if(conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
