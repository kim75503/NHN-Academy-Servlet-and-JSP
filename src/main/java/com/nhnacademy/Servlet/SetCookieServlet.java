package com.nhnacademy.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


@WebServlet(name ="setCookieServlet", value = "/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String locale = req.getParameter("locale");

        if(Objects.isNull(locale)){
            locale = "ko";
        }

        Cookie cookie = new Cookie("locale",locale);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);

        try(PrintWriter out = resp.getWriter()){
            out.println("OK");
        }
    }
}