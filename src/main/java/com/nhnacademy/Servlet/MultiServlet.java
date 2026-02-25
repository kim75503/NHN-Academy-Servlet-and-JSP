package com.nhnacademy.Servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name = "multiServlet", value = "/multi", loadOnStartup = 2)
public class MultiServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(MultiServlet.class.getName());
    @Override
    public void init(ServletConfig config) throws ServletException{
        log.info("init");
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        resp.setCharacterEncoding("utf-8");
        String[] values= req.getParameterValues("class");
        String url = getServletContext().getInitParameter("url");
        try(PrintWriter out = resp.getWriter()){
            out.println(String.join(",", values));
            out.printf("url:%s\n",url);
        }catch (IOException e){
            log.info(e.getMessage());
        }


    }

}
