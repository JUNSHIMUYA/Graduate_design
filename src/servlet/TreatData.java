package servlet;

import DAO.DataDao;
import DAO.RegDao;
import utils.RegUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/TreatData")
public class TreatData extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String secret="77524656";

        String R=request.getParameter("R");
        String R_N=request.getParameter("R_N");
        String address=request.getParameter("address");


        System.out.println("treatData");
        System.out.println(R);
        System.out.println(R_N);
        System.out.println(address);


        try {
            DataDao.Connect();
            DataDao.update_R_and_R_N(R,R_N,address);
            DataDao.insert_secert(address,secret);
            DataDao.CloseConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}

