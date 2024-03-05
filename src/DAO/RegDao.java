package DAO;

import utils.RegUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegDao {

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

    public static void Zhuce(RegUtils reg) throws Exception {
        Connect();
        PreparedStatement ps=con.prepareStatement("insert into reg(address,R,public_key) value(?,?,?)");
        ps.setString(1, reg.getAddress());
        ps.setString(2, reg.getR());
        ps.setString(3, reg.getPublic_key());
        int count=ps.executeUpdate();
    }

}

