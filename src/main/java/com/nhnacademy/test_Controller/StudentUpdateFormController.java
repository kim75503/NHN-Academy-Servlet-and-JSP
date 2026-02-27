package com.nhnacademy.test_Controller;

import com.nhnacademy.test_servlet.Student;
import com.nhnacademy.test_servlet.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;
@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentUpdateFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        if (studentRepository == null) {
            throw new ServletException("studentRepository not found");
        }

        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            throw new RuntimeException("id is required");
        }

        Student student = studentRepository.getStudentById(id); // 없으면 repo에서 예외 던질 수도 있음
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found : " + id);
        }

        req.setAttribute("student", student);

        return "/student/register.jsp";
    }
}
