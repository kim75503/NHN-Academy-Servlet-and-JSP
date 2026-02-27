package com.nhnacademy.example;

import java.util.LinkedList;
import java.util.List;

public class Example03 {

    public static void main(String[] args) {

        @SuppressWarnings({"unused"})
        String name="marco";

        System.out.println("hello marco");

        @SuppressWarnings({"unused","deprecation"})
        Integer a = new Integer(1);

        @SuppressWarnings({"rawtypes"})
        List list = new LinkedList<>();
        Box<String> box = new Box<>("marco");
        list.add(box);
    }

    public static class Box<T> {
        private final T t;

        public Box(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }
    }
}
