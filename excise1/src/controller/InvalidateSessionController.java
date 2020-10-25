package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/invalidateSessionController")
public class InvalidateSessionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie cookie=new Cookie("loginAlready","000");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        session.removeAttribute("currentUser");
        response.sendRedirect("login.html");
    }
}
