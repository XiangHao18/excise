package filter;

import dao.roleDao;
import dao.userRoleDao;
import dbc.DataBaseConnection;
import vo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionFilter implements Filter {
    private static DataBaseConnection dbc;
    private String notCheckPath;//不要用过滤的请求地址，从web.xml文件读取
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        String path=request.getServletPath();
        if (notCheckPath.indexOf(path)==-1){
            HttpSession session=request.getSession();
            if (session.getAttribute("currentUser")==null){
                request.setAttribute("info","请先登录再访问");
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }else{
                dbc=new DataBaseConnection();
                userRoleDao userRoleDao=new userRoleDao(dbc.getConnection());
                User user= (User) session.getAttribute("currentUser");
                int roleId=userRoleDao.getRoleId(user.getUsername());
                roleDao roleDao=new roleDao(dbc.getConnection());
                String roleName=roleDao.getRoleName(roleId);
                System.out.println(roleName);
                dbc.close();
                if(roleName.equals("普通用户")&&path.equals("/download.jsp")){
                    request.setAttribute("info","你的用户权限不足");
                    RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");
                    rd.forward(request,response);
                }else {
                    chain.doFilter(req,resp);
                }
            }
        }else {
            System.out.println("请求地址url-pattern:"+path);
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        notCheckPath=config.getInitParameter("notCheckUri");
    }

}
