package com.nhnacademy.test_Controller;

import com.nhnacademy.stopwatch.StopWatch;
import com.nhnacademy.test_servlet.Student;
import com.nhnacademy.test_servlet.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;


@StopWatch
@Slf4j
@RequestMapping(value = "/student/list.do", method = RequestMapping.Method.GET)
public class StudentListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long start, stop;
        start = System.currentTimeMillis();


        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList",studentList);
        stop = System.currentTimeMillis();

        log.info("total time = {}", stop - start);
        return "/student/list.jsp";
    }
}
