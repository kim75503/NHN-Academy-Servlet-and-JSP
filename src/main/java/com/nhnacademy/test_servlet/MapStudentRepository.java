package com.nhnacademy.test_servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        validateStudent(student);
        String id = student.getId();
        validateId(id);

        Student prev = studentsMap.putIfAbsent(id, student);
        if(prev != null){
            throw  new IllegalArgumentException("이미 존재하는 아이디 입니다. id=" + id);
        }
    }

    @Override
    public void update(Student student) {
        validateStudent(student);
        String id = student.getId();
        validateId(id);

        Student prev = studentsMap.replace(id, student);
        if(prev == null){
            throw new IllegalArgumentException("해당 학생을 찾을 수 없습니다. id="+ id);
        }
    }

    @Override
    public void deleteById(String id) {
        validateId(id);

        Student removed = studentsMap.remove(id);
        if (removed == null) {
            throw new IllegalArgumentException("Student not found. id=" + id);
        }
    }

    @Override
    public Student getStudentById(String id) {
        validateId(id);

        Student student = studentsMap.get(id);
        if (student == null) {
            throw new IllegalArgumentException("Student not found. id=" + id);
        }
        return student;
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(studentsMap.values());
    }

    @Override
    public boolean existById(String id) {
        if (id == null) return false;
        return studentsMap.containsKey(id);
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }
    }
}
