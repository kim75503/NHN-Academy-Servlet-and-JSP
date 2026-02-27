package com.nhnacademy.test_servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalDateTime;
import java.util.Random;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        Random random = new Random();

        for(int i=1; i<=10; i++){
            String id = "student"+i;
            int age = random.nextInt(20,31);
            LocalDateTime createdAt = LocalDateTime.now();

            Gender sex;
            if(i % 3 == 0) sex = Gender.M;
            else sex = Gender.F;
            Student student = new Student(id, "아카데미"+i,sex, age, createdAt);


            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30

            studentRepository.save(student);

        }
        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
    }
}