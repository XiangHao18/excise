package controller;

import dao.CreateVerificationCodeImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/createVerifyCodeImageServlet")
public class CreateVerifyCodeImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把验证码图片生成的过程封装在tools包下的CreateVerificationCodeImage类中
        CreateVerificationCodeImage createVCodeImage=new CreateVerificationCodeImage();
        //产生4位随机字符串
        String vCode=createVCodeImage.createCode();
        //获取httpSession对象
        HttpSession session=request.getSession();
        //将产生的四位随机字符串存放于session中(存放在session中的数据在一个会话范围内，多个程序全局共享)，以便验证
        session.setAttribute("verifyCode",vCode);
        //设置返回的内容
        response.setContentType("img/jpeg");
        //浏览器不缓存响应内容--验证码图片，点一次就要刷新一次，所以不能有缓存出现
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        //设置验证码失效时间
        response.setDateHeader("Expires",0);
        //以字节流发过去，交给img的src属性去解析即可
        BufferedImage image=createVCodeImage.CreateImage(vCode);
        ServletOutputStream out=response.getOutputStream();
        ImageIO.write(image,"JPEG",out);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把验证码图片生成的过程封装在tools包下的CreateVerificationCodeImage类中
        CreateVerificationCodeImage createVCodeImage=new CreateVerificationCodeImage();
        //产生4位随机字符串
        String vCode=createVCodeImage.createCode();
        //获取httpSession对象
        HttpSession session=request.getSession();
        //将产生的四位随机字符串存放于session中(存放在session中的数据在一个会话范围内，多个程序全局共享)，以便验证
        session.setAttribute("verifyCode",vCode);
        //设置返回的内容
        response.setContentType("img/jpeg");
        //浏览器不缓存响应内容--验证码图片，点一次就要刷新一次，所以不能有缓存出现
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        //设置验证码失效时间
        response.setDateHeader("Expires",0);
        //以字节流发过去，交给img的src属性去解析即可
        BufferedImage image=createVCodeImage.CreateImage(vCode);
        ServletOutputStream out=response.getOutputStream();
        ImageIO.write(image,"JPEG",out);
        out.flush();
        out.close();
    }
}
