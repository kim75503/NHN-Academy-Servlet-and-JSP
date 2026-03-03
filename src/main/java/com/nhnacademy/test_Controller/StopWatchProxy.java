package com.nhnacademy.test_Controller;

import com.nhnacademy.stopwatch.StopWatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StopWatchProxy implements Command{
    private final Command command;
    public StopWatchProxy(final Command command){
        this.command =command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//        long start, stop;
//        start = System.currentTimeMillis();
//
//        String view = command.execute(req, resp);
//        stop = System.currentTimeMillis();
//        log.info("total time = {}", stop - start);
//        if (view == null || view.isBlank()) {
//            throw new ServletException("view is null/blank");
//        }
//
//        return view;

        if(command.getClass().isAnnotationPresent(StopWatch.class)){
            long start, stop;
        start = System.currentTimeMillis();

        String view = command.execute(req, resp);
        stop = System.currentTimeMillis();
        log.info("total time = {}", stop - start);
        return view;
        }else {
            return command.execute(req,resp);
        }
    }
}
