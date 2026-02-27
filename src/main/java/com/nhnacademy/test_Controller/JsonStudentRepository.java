package com.nhnacademy.test_Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.test_servlet.Student;
import com.nhnacademy.test_servlet.StudentRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class JsonStudentRepository implements StudentRepository {

    private final ObjectMapper objectMapper;

    // json file 저장 경로
    private static final String JSON_FILE_PATH =
            "/Users/nhn/IdeaProjects/servlet:jsp/student/src/main/json/student.json";

    public JsonStudentRepository() {
        objectMapper = new ObjectMapper();
        // LocalDateTime 직렬화/역직렬화
        objectMapper.registerModule(new JavaTimeModule());

        // todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.
        File file = new File(JSON_FILE_PATH);
        if (file.exists()) {
            // 새로 시작용이면 삭제
            file.delete();
        }

        // 폴더 없으면 생성 (안 하면 FileWriter에서 실패)
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    private synchronized List<Student> readJsonFile() {
        File file = new File(JSON_FILE_PATH);

        // todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        // json read & 역직렬화 ( json string -> Object )
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            return objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<Student> studentList) {
        File file = new File(JSON_FILE_PATH);

        // 폴더 없으면 생성
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            objectMapper.writeValue(bw, studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        if (student == null || student.getId() == null || student.getId().isBlank()) {
            throw new IllegalArgumentException("student/id is required");
        }
        List<Student> students = readJsonFile();

        if (students.stream().anyMatch(s -> s.getId().equals(student.getId()))) {
            throw new IllegalStateException("Student already exists. id=" + student.getId());
        }

        students.add(student);
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        if (student == null || student.getId() == null || student.getId().isBlank()) {
            throw new IllegalArgumentException("student/id is required");
        }

        List<Student> students = readJsonFile();
        boolean updated = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new NoSuchElementException("Student not found. id=" + student.getId());
        }

        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }

        List<Student> students = readJsonFile();
        boolean removed = students.removeIf(s -> id.equals(s.getId()));

        if (!removed) {
            throw new NoSuchElementException("Student not found. id=" + id);
        }

        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }

        return readJsonFile().stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Student not found. id=" + id));
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        if (id == null || id.isBlank()) return false;
        return readJsonFile().stream().anyMatch(s -> id.equals(s.getId()));
    }
}