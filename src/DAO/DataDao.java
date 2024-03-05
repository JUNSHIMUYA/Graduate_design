package DAO;

import java.sql.*;

public class DataDao {
    public static Connection con=null;
    //定义静态方法可以通过类名访问
    public static void Connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/graduate?serverTimezone=UTC";
        String password="123456";
        String username="root";
        con= DriverManager.getConnection(url, username, password);
    }
    public static void CloseConnect() throws SQLException
    {
        con.close();
    }

    public static void update_R_and_R_N(String R,String R_N,String address) throws Exception {

        PreparedStatement ps=con.prepareStatement("update reg set R=? where R=?");

        ps.setString(1,R_N);
        ps.setString(2,R);
        int count=ps.executeUpdate();

        PreparedStatement ps2=con.prepareStatement("insert into auth_local(address,R_N) value(?,?)");
        ps2.setString(1,address);
        ps2.setString(2,R_N);
        int count2=ps2.executeUpdate();

    }

    public static void insert_secert(String address,String secret) throws Exception {

        PreparedStatement ps3=con.prepareStatement("insert into auth(address,secert) value(?,?)");
        ps3.setString(1,address);
        ps3.setString(2,secret);
        int count3=ps3.executeUpdate();

    }

}
