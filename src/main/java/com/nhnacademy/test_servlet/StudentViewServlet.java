package com.nhnacademy.test_servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
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

        String id = req.getParameter("id");
        if (Objects.isNull(id) || id.isBlank()) {
            throw new RuntimeException("parameter [id] : null ");
        }

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new IllegalArgumentException(id);
        }

        log.info("student: {}", student);
        req.setAttribute("student", student);

        // ✅ forward 대신 view attribute 설정
        req.setAttribute("view", "/student/view.jsp");
    }
}