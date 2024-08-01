package com.simon.student.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.student.model.dto.StudentDto;
import com.simon.student.model.entity.Student;
import com.simon.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static com.simon.student.utils.TestEntityFactory.SCHOOL_ID;
import static com.simon.student.utils.TestEntityFactory.getStudentDto;
import static com.simon.student.utils.TestEntityFactory.getStudents;
import static com.simon.student.utils.Utility.getObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;
    private ObjectMapper objectMapper=getObjectMapper();


    @Test
    void saveStudentTest() {
        StudentDto studentDto=getStudentDto();
        Student student=objectMapper.convertValue(studentDto,Student.class);

        studentService.saveStudent(studentDto);

        verify(studentRepository).save(student);
    }

    @Test
    void findAllStudentsTest() {
        List<Student> students=getStudents();
        List<StudentDto>studentDtos=objectMapper.convertValue(students,new TypeReference<List<StudentDto>>() {});

        when(studentRepository.findAll()).thenReturn(students);

        List<StudentDto> result = studentService.findAllStudents();
        assertEquals(studentDtos, result);
    }

    @Test
    void findAllStudentsBySchoolTest() {
        Long schoolId=SCHOOL_ID;
        List<Student>students=getStudents();
        List<StudentDto>studentDtos=objectMapper.convertValue(students,new TypeReference<List<StudentDto>>() {});

        when(studentRepository.findAllBySchoolId(any(Long.class))).thenReturn(students);

        List<StudentDto> result = studentService.findAllStudentsBySchool(schoolId);
        assertEquals(studentDtos, result);
    }
}