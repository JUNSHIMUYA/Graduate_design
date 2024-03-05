package DAO;

import java.sql.*;

public class Auth_localDao {

    public static Connection con=null;
    //定义静态方法可以通过类名访问
    public static void Connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/graduate?serverTimezone=UTC";
        String password="123456";
        String username="root";
        con=DriverManager.getConnection(url, username, password);
    }
    public static void CloseConnect() throws SQLException
    {
        con.close();
    }

    public static boolean auth_local(String R_N) throws Exception {

        PreparedStatement ps=con.prepareStatement("select * from auth_local where R_N=?");
        ps.setString(1,R_N);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            return true;
        }
        else
        {
            System.out.println("auth_local_fail");
            return false;
        }
    }

    public static void auth_local_update(String R_N,String R) throws Exception {
        PreparedStatement ps2=con.prepareStatement("update auth_local set R_N=? where R_N=?");
        ps2.setString(1,R_N);
        ps2.setString(2,R);
        int count=ps2.executeUpdate();

    }

}