package com.nhnacademy.test_Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static jakarta.servlet.RequestDispatcher.*;
import static jakarta.servlet.RequestDispatcher.ERROR_REQUEST_URI;

@RequestMapping(value = "/error.do")
public class ErrorController implements Command{


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        return "/error.jsp";
    }
}
