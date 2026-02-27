package com.nhnacademy.test_Controller;

import com.nhnacademy.test_servlet.Student;
import com.nhnacademy.test_servlet.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
public class StudentViewController implements Command{
    private static Logger log = Logger.getLogger(StudentDeleteController.class.getName());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        if (studentRepository == null) {
            throw new ServletException("studentRepository not found");
        }
        String id = req.getParameter("id");
        if (Objects.isNull(id) || id.isBlank()) {
            throw new RuntimeException("parameter [id] : null ");
        }

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new IllegalArgumentException(id);
        }

        log.info("student: {}"+ student);
        req.setAttribute("student", student);

        return "/student/view.jsp";
    }
}
