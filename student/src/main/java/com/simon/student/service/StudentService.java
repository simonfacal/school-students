package com.simon.student.service;

import com.simon.student.model.dto.StudentDto;
import com.simon.student.model.entity.Student;

import java.util.List;

public interface StudentService {
    public void saveStudent(StudentDto studentDto);

    public List<StudentDto> findAllStudents();

    public List<StudentDto> findAllStudentsBySchool(Long schoolId);
}
