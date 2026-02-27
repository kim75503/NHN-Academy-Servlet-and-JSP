package com.nhnacademy.test_Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentRegisterFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        return "/student/register.jsp";
    }
}