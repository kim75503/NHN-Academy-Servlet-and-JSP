package com.nhnacademy.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name = "beautifyServlet", value = "/beautify", loadOnStartup = 3)
public class BeautifyServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(BeautifyServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String html = request.getParameter("html");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try(PrintWriter writer = response.getWriter()){
            writer.println(Jsoup.parse(html));
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
