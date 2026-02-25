package com.nhnacademy.Filter;

public class NotFoundResponse implements Response {
    @Override
    public void doResponse(Request request) {
        System.out.println("###### response:NotFoundResponse #####");
        System.out.println("요청 경로: " + request.getPath());
        System.out.println("존재하지 않는 페이지!");
    }
}