package com.nhnacademy.Servlet;


import jakarta.servlet.ServletContext;
import java.util.Optional;


public final class CounterUtils{
    private CounterUtils(){
        throw new IllegalArgumentException("Utility class");
    }

    public static void increaseCounter(ServletContext context){
        Long counter = Optional.ofNullable((Long)context.getAttribute("counter"))
                .orElse(0L);
        counter++;
        context.setAttribute("counter", counter);
    }


}