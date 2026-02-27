package com.nhnacademy.test_servlet;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Student {
    //아이디
    private  String id;
    //이름
    private  String name;
    //성별
    private  Gender gender;
    //나이
    private  int age;
    //생성일
    private LocalDateTime createdAt;

    // ... java beans 특징을 고려하여 작성합니다.

    public Student(){

    }

    public Student(String id, String name, Gender gender, int age, LocalDateTime createdAt){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }

    public void setId(String id){ this.id = id;}
    public void setName(String name){this.name = name;}
    public void setGender(Gender gender){this.gender = gender;}
    public void setAge(int age){this.age = age;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt = createdAt;}



}

