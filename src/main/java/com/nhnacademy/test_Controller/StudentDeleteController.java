package com.nhnacademy.test_Controller;

import com.nhnacademy.test_servlet.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;


@Slf4j
@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long start, stop;
        start = System.currentTimeMillis();
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        log.info("id:{}",id);
        studentRepository.deleteById(id);
        //view를 return 합니다.

        stop = System.currentTimeMillis();
        log.info("total time = {}", stop - start);
        return "redirect:/student/list.do";
    }
}
