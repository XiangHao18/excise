package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UserDao;
import dbc.DataBaseConnection;
import vo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
    private static DataBaseConnection dbc;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置请求参数编码格式为utf-8,防止中文参数乱码
        request.setCharacterEncoding("utf-8");
        //按照表单的各元素的name属性值获取各请求参数值
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String vcode=request.getParameter("vcode");
        String checkBox=request.getParameter("checkBox");
        //获取HttpSession对象
        HttpSession session=request.getSession();
        //取出CreateVerifyImageController中存放的验证码字符串
        String saveVcode=(String) session.getAttribute("verifyCode");
        String forwardPath="";//转发程序的url-pattern
        //比较输入的验证码和随机生成的验证码是否相同
        if(!vcode.equalsIgnoreCase(saveVcode)){//不同
            //将需要传递的数据存放在request域范围中，目标转发页面可以从中获取
            request.setAttribute("info","验证码不正确！");
            forwardPath="/error.jsp";
        }else {//验证码正确
            dbc=new DataBaseConnection();
            UserDao userDao=new UserDao(dbc.getConnection());
            if(userDao.get(userName)==null){//用户名不存在
                //将需要传递的数据存放在request域范围中，目标转发页面可以从中获取
                request.setAttribute("info","您输入的用户名不存在！");
                forwardPath="/error.jsp";
            }else{//用户名存在
                User currentUser=userDao.get(userName);
                if(!(currentUser.getPassword().equals(password))){//密码不正确
                    //将需要传递的数据存放在request域范围中，目标转发页面可以从中获取
                    request.setAttribute("info","您输入的密码不正确！");
                    forwardPath="/error.jsp";
                }else {//用户名密码正确
                    //将需要传递的数据存放在session域范围中，一个会话阶段的所有程序都可以从中获取
                    if (checkBox!=null&&checkBox.equals("on")) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setPrettyPrinting();
                        Gson gson = gsonBuilder.create();
                        String userInfo = gson.toJson(currentUser);
                        userInfo = URLEncoder.encode(userInfo, "UTF-8");
                        System.out.println(userInfo);
                        Cookie cookie = new Cookie("loginAlready", userInfo);
                        cookie.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie);
                    }
                    session.setAttribute("currentUser",currentUser);
                    forwardPath="/main.jsp";
                }
            }
            dbc.close();
        }
        //获取转发对象
        RequestDispatcher rd=request.getRequestDispatcher(forwardPath);
        //请求转发到目标程序
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
