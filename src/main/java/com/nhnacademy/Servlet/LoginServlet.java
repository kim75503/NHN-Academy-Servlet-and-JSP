package com.nhnacademy.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
@WebServlet(name = "loginServlet", value = "/login",
        initParams = {
                @WebInitParam(name = "id", value = "Minseo"),
                @WebInitParam(name = "pwd", value = "1234")
        })
public class LoginServlet extends HttpServlet {
    private String initParamId;
    private String initParamPwd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        initParamId = config.getInitParameter("id");
        initParamPwd = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id")) ){
            resp.sendRedirect("/login.html");
        }else{
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("</head>");
                out.println("<body>");
                out.println("login success : id =" + session.getAttribute("id") + "<br/>");
                out.println("<a href='/logout'>logout</a>");
                out.println("</body>");
                out.println("</html>");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

//        Cookie cookie = new Cookie("admin", "1234");
//
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);

        if(initParamId.equals(id) && initParamPwd.equals(pwd)){
            //session 있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();

            session.setAttribute("id",id);
            resp.sendRedirect("/login");
        }else{
            log.error("아이디/패스워드가 일치하지 않습니다.");
            //resp.sendRedirect("/login.html");


            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/loginfail.jsp");
            rd.forward(req,resp);

        }
    }

}
