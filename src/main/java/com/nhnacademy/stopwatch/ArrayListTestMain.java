package com.nhnacademy.stopwatch;

public class ArrayListTestMain {
    public static void main(String[] args) {
        PerformanceTestable arrayListTest = new ArrayListTest();
        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy(arrayListTest);
        arrayListTestProxy.test();
    }
}
