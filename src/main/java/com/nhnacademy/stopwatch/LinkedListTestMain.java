package com.nhnacademy.stopwatch;

public class LinkedListTestMain {
    public static void main(String[] args) {
        PerformanceTestable linkedListTest = new LinkedListTest();
        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy(linkedListTest);
        arrayListTestProxy.test();
    }
}
