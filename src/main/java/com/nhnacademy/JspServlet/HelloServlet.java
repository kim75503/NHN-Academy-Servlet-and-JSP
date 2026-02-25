package com.nhnacademy.JspServlet;

import java.io.*;
import java.util.logging.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = {"/hello", "/hello-servlet"}, loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "title", value = "Mr."),
                @WebInitParam(name = "name", value = "marco")
        }
)
public class HelloServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(HelloServlet.class.getName());
    private String message;

    @Override
    public void init(ServletConfig config)throws ServletException {
        log.info("before init!");
        super.init(config);
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");

        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");

        try(PrintWriter writer = response.getWriter()){
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
                writer.println("<head>");
                    writer.println("<meta charset='utf-8'>");
                writer.println("</head>");
                writer.println("<body>");
                    writer.println();
                    writer.println("<h1>" + message + "</h1>");
                    writer.println("<h1>hello servlet!</h1>");
                    writer.println("<h1>안녕 서블릿!</h1>");
                    writer.println("<h1>Hello " + title + " " + name + "</h1>");
                writer.println("</body>");
            writer.println("</html>");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("before service!");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        log.info("before dstroy!");
        super.destroy();
    }
}