package com.nhnacademy.test_servlet;

import com.nhnacademy.test_Controller.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static jakarta.servlet.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 공통 처리
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            if (command == null) {
                throw new ServletException("No command mapping for: " + req.getMethod() + " " + req.getServletPath());
            }

            String view = command.execute(req, resp);
            if (view == null || view.isBlank()) {
                throw new ServletException("view is null/blank");
            }

            if (view.startsWith(REDIRECT_PREFIX)) {
                String redirectUrl = view.substring(REDIRECT_PREFIX.length());
                log.info("redirect-url : {}", redirectUrl);
                resp.sendRedirect(req.getContextPath() + redirectUrl);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }

        } catch (Exception ex) {
            // 공통 error 처리
            log.error("FrontServlet error", ex);

            // ErrorServlet 방식처럼 세팅(있으면 기존 ERROR_*도 같이 활용 가능)
            req.setAttribute("status_code", 500);
            req.setAttribute("exception_type", ex.getClass());
            req.setAttribute("message", ex.getMessage());
            req.setAttribute("exception", ex);
            req.setAttribute("request_uri", req.getRequestURI());

            RequestDispatcher rd = req.getRequestDispatcher("/student/error.jsp");
            rd.forward(req, resp);
        }
    }

    private Command resolveCommand(String servletPath, String method) {
        Command command = null;

        if ("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentListController();
        } else if ("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentViewController();
        } else if ("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentRegisterFormController();
        } else if ("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new StudentRegisterController();
        } else if ("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new StudentUpdateFormController();
        } else if ("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new StudentUpdateController();
        } else if ("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new StudentDeleteController();
        } else if ("/error.do".equals(servletPath)) {
            command = new ErrorController();
        }

        return command;
    }
}