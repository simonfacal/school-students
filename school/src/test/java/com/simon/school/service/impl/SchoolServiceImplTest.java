package com.simon.school.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.school.client.StudentClient;
import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;
import com.simon.school.model.dto.StudentDto;
import com.simon.school.model.entity.School;
import com.simon.school.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static com.simon.school.utils.TestEntityFactory.SCHOOL_ID;
import static com.simon.school.utils.TestEntityFactory.getSchool;
import static com.simon.school.utils.TestEntityFactory.getSchoolDto;
import static com.simon.school.utils.TestEntityFactory.getSchools;
import static com.simon.school.utils.TestEntityFactory.getStudentDtos;
import static com.simon.school.utils.Utility.getObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchoolServiceImplTest {
    @Mock
    private SchoolRepository schoolRepository;
    @InjectMocks
    private SchoolServiceImpl schoolService;
    @Mock
    private StudentClient studentClient;
    private ObjectMapper objectMapper=getObjectMapper();


    @Test
    void saveSchoolTest() {
        SchoolDto schoolDto=getSchoolDto();
        School school=objectMapper.convertValue(schoolDto,School.class);

        schoolService.saveSchool(schoolDto);

        verify(schoolRepository).save(school);

    }

    @Test
    void findAllSchoolsTest() {
        List<School>schools=getSchools();
        TypeReference<List<SchoolDto>> typeRef = new TypeReference<>() {};
        List<SchoolDto> schoolDtos= objectMapper.convertValue(schools,typeRef);

      when(schoolRepository.findAll()).thenReturn(schools);

       List<SchoolDto> result=schoolService.findAllSchools();

        assertEquals(schoolDtos,result);
    }

    @Test
    void findSchoolWithStudentsTest() {
        Long schoolId= SCHOOL_ID;
        School school=getSchool();
        List<StudentDto>studentDtos=getStudentDtos();

        when(schoolRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(school));
        when(studentClient.findAllStudentsBySchool(any(Long.class))).thenReturn(studentDtos);

        FullSchoolResponse fullSchoolResponse= FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(studentDtos)
                .build();

        FullSchoolResponse result=schoolService.findSchoolWithStudents(schoolId);

        assertEquals(fullSchoolResponse,result);

    }
}