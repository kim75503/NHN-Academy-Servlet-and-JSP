package com.nhnacademy.test_servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
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
        if (id == null || id.isBlank()) {
            throw new RuntimeException("id is required");
        }

        Student student = studentRepository.getStudentById(id); // 없으면 repo에서 예외 던질 수도 있음
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found : " + id);
        }

        req.setAttribute("student", student);

        // ✅ forward 대신 view attribute
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        if (Objects.nonNull(req.getParameter("gender")) && !req.getParameter("gender").isBlank()) {
            gender = Gender.valueOf(req.getParameter("gender"));
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

        Student oldStudent = studentRepository.getStudentById(id);

        Student student = new Student(id, name, gender, age, oldStudent.getCreatedAt());
        studentRepository.update(student);

        req.setAttribute("view", "redirect:/student/view.do?id=" + student.getId());
    }
}