package com.nhnacademy.Filter;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}