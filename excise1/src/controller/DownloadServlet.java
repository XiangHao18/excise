package controller;

import dao.DownloadDao;
import dbc.DataBaseConnection;
import vo.Download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    private static DataBaseConnection dbc;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        dbc=new DataBaseConnection();
        String id = request.getParameter("id");
        DownloadDao dao = new DownloadDao(dbc.getConnection());
        try {
            Download download = dao.get(Integer.parseInt(id));
            String path = request.getServletContext().getRealPath("/file/word.docx");
            String fileName = path.substring(path.lastIndexOf("\\") + 1);
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            InputStream in = new FileInputStream(path);
            int len = 0;
            byte[] buffer = new byte[1024];
            ServletOutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbc.close();
        }
    }
}
