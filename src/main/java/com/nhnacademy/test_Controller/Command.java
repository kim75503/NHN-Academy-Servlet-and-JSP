package com.nhnacademy.test_Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException;
}
