package servlet;

import DAO.Auth_localDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/TreatAuth")
public class TreatAuth extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String R=request.getParameter("input_R");
        String R_N=request.getParameter("input_R_N");

        System.out.println(R);
        System.out.println(R_N);

        try {
            Auth_localDao.Connect();
            if(Auth_localDao.auth_local(R)){
                Auth_localDao.auth_local_update(R_N,R);
                String url="/Auth_local_success.html";
                RequestDispatcher rd=request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else
            {
                String url="/auth_c.html";
                RequestDispatcher rd=request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            Auth_localDao.CloseConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
