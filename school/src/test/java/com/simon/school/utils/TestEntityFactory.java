package com.simon.school.utils;


import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;
import com.simon.school.model.dto.StudentDto;
import com.simon.school.model.entity.School;

import java.util.List;



public class TestEntityFactory {
    public static final Long SCHOOL_ID =1L;
    public static final String SCHOOL_NAME ="Isaac Newton School";
    public static final String SCHOOL_EMAIL ="isaacnewton@gmail.com";
    public static final Long SCHOOL_ID2 =2L;
    public static final String SCHOOL_NAME2 ="Albert Einstein School";
    public static final String SCHOOL_EMAIL2 ="alberteinstein@gmail.com";



    public static School getSchool(){
        return School.builder()
                .id(SCHOOL_ID)
                .name(SCHOOL_NAME)
                .email(SCHOOL_EMAIL)
                .build();
    }

    public static SchoolDto getSchoolDto(){
        return SchoolDto.builder()
                .name(SCHOOL_NAME)
                .email(SCHOOL_EMAIL)
               .build();
    }

    public static School getSchool2(){
        return School.builder()
                .id(SCHOOL_ID2)
                .name(SCHOOL_NAME2)
                .email(SCHOOL_EMAIL2)
                .build();
    }

    public static SchoolDto getSchoolDto2(){
        return SchoolDto.builder()
                .name(SCHOOL_NAME2)
                .email(SCHOOL_EMAIL2)
                .build();
    }


    public static List<School> getSchools(){
        List<School>schools=List.of(getSchool(),getSchool2());
        return schools;
    }

    public static List<SchoolDto> getSchoolDtos(){
        List<SchoolDto> schoolDtos=List.of(getSchoolDto(),getSchoolDto2());
        return schoolDtos;
    }

    public static StudentDto getStudentDto(){
        return StudentDto.builder()
               .firstname("Ivan")
                .lastname("Gonzalez")
               .email("ivan@gmail.com")
               .build();
    }
    public static StudentDto getStudentDto2(){
        return StudentDto.builder()
                .firstname("Sofia")
                .lastname("Palladino")
                .email("sofi@gmail.com")
                .build();
    }

    public static List<StudentDto>getStudentDtos(){
        List<StudentDto>studentDtos=List.of(getStudentDto(),getStudentDto2());
        return studentDtos;
    }


    public static FullSchoolResponse getFullSchoolResponse(){
       return FullSchoolResponse.builder()
               .name(SCHOOL_NAME)
               .email(SCHOOL_EMAIL)
               .students(getStudentDtos())
               .build();
    }
}
