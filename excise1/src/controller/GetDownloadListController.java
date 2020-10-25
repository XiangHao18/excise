package controller;

import dao.DownloadDao;
import dbc.DataBaseConnection;
import vo.Download;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/getDownloadList.do")
public class GetDownloadListController extends HttpServlet {
    private static DataBaseConnection dbc;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbc=new DataBaseConnection();
        //System.out.println("1111");
        DownloadDao downloadDao=new DownloadDao(dbc.getConnection());
        try {
            List<Download> downloadList = downloadDao.selectAll();
            //System.out.println(downloadList);
            HttpSession session=request.getSession();
            session.setAttribute("downloadList",downloadList);
            response.sendRedirect("download.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            dbc.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
