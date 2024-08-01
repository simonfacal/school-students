package com.simon.student.utils;

import com.simon.student.model.dto.StudentDto;
import com.simon.student.model.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class TestEntityFactory {
    public static final Long STUDENT_ID=1L;
    public static final String FIRSTNAME="Peter";
    public static final String LASTNAME="Parker";
    public static final String EMAIL="peterparker@gmail.com";
    public static final Long SCHOOL_ID=1L;
    public static final Long STUDENT_ID2=2L;
    public static final String FIRSTNAME2="Mary";
    public static final String LASTNAME2="Jane";
    public static final String EMAIL2="maryjane@gmail.com";
    public static final Long SCHOOL_ID2=2L;


    public static Student getStudent(){
        return Student.builder()
                .id(STUDENT_ID)
                .firstname(FIRSTNAME)
                .lastname(LASTNAME)
                .email(EMAIL)
                .schoolId(SCHOOL_ID)
                .build();
    }

    public static StudentDto getStudentDto(){
        return StudentDto.builder()
                .firstname(FIRSTNAME)
                .lastname(LASTNAME)
                .email(EMAIL)
                .schoolId(SCHOOL_ID)
                .build();
    }

    public static Student getStudent2(){
        return Student.builder()
                .id(STUDENT_ID2)
                .firstname(FIRSTNAME)
                .lastname(LASTNAME)
                .email(EMAIL)
                .schoolId(SCHOOL_ID)
                .build();
    }

    public static StudentDto getStudentDto2(){
        return StudentDto.builder()
                .firstname(FIRSTNAME2)
                .lastname(LASTNAME2)
                .email(EMAIL2)
                .schoolId(SCHOOL_ID2)
                .build();
    }

    public static List<Student> getStudents(){
        List<Student> students=new ArrayList<>();
        students.add(getStudent());
        students.add(getStudent2());
        return students;
    }

    public static List<StudentDto> getStudentDtos(){
        List<StudentDto>studentDtos=new ArrayList<>();
        studentDtos.add(getStudentDto());
        studentDtos.add(getStudentDto2());
        return studentDtos;
    }
    
}
