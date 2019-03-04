package com.github.wangguocheng.mybatis.demo.mapper;

import com.github.wangguocheng.mybatis.demo.domain.Student;

import java.util.List;

public interface StudentMapper {
	
	public void insertStudent(Student student);

	public List<Student> findAllStudents();
	
	public Student findStudentById(long id);

	void updateStudentById(Student student);
}
