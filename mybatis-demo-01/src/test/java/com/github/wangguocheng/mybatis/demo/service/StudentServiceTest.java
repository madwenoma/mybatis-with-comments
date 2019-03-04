package com.github.wangguocheng.mybatis.demo.service;

import com.github.wangguocheng.mybatis.demo.domain.Student;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class StudentServiceTest {

    private static StudentService studentService;

    @BeforeClass
    public static void setup() {
        studentService = new StudentService();
    }

    @AfterClass
    public static void tearDown() {
        studentService = null;
    }

    @Test
    public void testFindAllStudents() {
        List<Student> students = studentService.findAllStudents();
        Assert.assertNotNull(students);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testFindStudentById() {
        Student student = studentService.findStudentById(1L);
        Assert.assertNotNull(student);
        System.out.println(student);
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setName("new-student-02");
        student.setEmail("new-student-02@163.com");
        student.setDob(new Date());
        studentService.createStudent(student);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setId(3);
        student.setName("TomWang");
        student.setEmail("tom@163.com");
        student.setDob(new Date());
        studentService.updateStudent(student);
    }

}
