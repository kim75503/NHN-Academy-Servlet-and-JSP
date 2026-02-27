package com.nhnacademy.test_servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        if (studentRepository == null) {
            throw new ServletException("studentRepository not found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // todo view attribute 설정 - /student/register.jsp
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        if (Objects.nonNull(req.getParameter("gender")) && !req.getParameter("gender").isBlank()) {
            gender = Gender.valueOf(req.getParameter("gender")); // "M" or "F"
        }

        Integer age = null;
        if (Objects.nonNull(req.getParameter("age")) && !req.getParameter("age").isBlank()) {
            age = Integer.parseInt(req.getParameter("age"));
        }

        if (Objects.isNull(id) || id.isBlank()
                || Objects.isNull(name) || name.isBlank()
                || Objects.isNull(gender)
                || Objects.isNull(age)) {
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }

        if (studentRepository.existById(id)) {
            throw new RuntimeException("이미 존재하는 아이디입니다. id=" + id);
        }

        // todo save 구현
        Student student = new Student(id, name, gender, age, LocalDateTime.now());
        studentRepository.save(student);

        // todo redirect view attribute 설정 (FrontServlet이 sendRedirect 처리)
        req.setAttribute("view", "redirect:/student/view.do?id=" + student.getId());
    }
}