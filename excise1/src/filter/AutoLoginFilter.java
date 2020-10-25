package filter;

import com.google.gson.Gson;
import dao.UserDao;
import dbc.DataBaseConnection;
import vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter(urlPatterns = "/login.html")
public class AutoLoginFilter implements Filter {
    private static DataBaseConnection dbc;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        String path=request.getServletPath();
        HttpSession session=request.getSession();
        Cookie[] cookies=request.getCookies();
        for (Cookie c:cookies) {
            String name=c.getName();
            if(path.equals("/login.html")&&"loginAlready".equals(name)){
                String userInfo=c.getValue();
                userInfo= URLDecoder.decode(userInfo,"UTF-8");
                User user=new Gson().fromJson(userInfo,User.class);
                System.out.println(user.toString());
                dbc=new DataBaseConnection();
                UserDao userDao=new UserDao(dbc.getConnection());
                User user1=userDao.get(user.getUsername());
                dbc.close();
                if(user1!=null){
                    session.setAttribute("currentUser",user);
                    RequestDispatcher rd=request.getRequestDispatcher("/main.jsp");
                    rd.forward(request,response);
                }else{
                    request.setAttribute("info","自动登录失败");
                    RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");
                    rd.forward(request,response);
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
