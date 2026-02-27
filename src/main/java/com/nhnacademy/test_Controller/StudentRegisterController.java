package com.nhnacademy.test_Controller;

import com.nhnacademy.test_servlet.Gender;
import com.nhnacademy.test_servlet.Student;
import com.nhnacademy.test_servlet.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;


@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.POST)
public class StudentRegisterController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        if (studentRepository == null) {
            throw new ServletException("studentRepository not found");
        }

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


        Student student = new Student(id, name, gender, age, LocalDateTime.now());
        studentRepository.save(student);

        req.setAttribute("view", "redirect:/student/view.do?id=" + student.getId());

        return "redirect:/student/view.do?id="+ student.getId();
    }
}
