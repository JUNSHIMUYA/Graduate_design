package servlet;

import DAO.RegDao;
import utils.RegUtils;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TreatReg")
public class TreatReg extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address=request.getParameter("address");
        String R=request.getParameter("R");
        String public_key=request.getParameter("public_key");

        RegUtils reg=new RegUtils();
        reg.setAddress(address);
        reg.setPublic_key(public_key);
        reg.setR(R);

        try {
            RegDao.Zhuce(reg);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                RegDao.CloseConnect();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            RegDao.CloseConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
