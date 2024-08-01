package com.simon.school.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;
import com.simon.school.model.entity.School;
import com.simon.school.repository.SchoolRepository;
import com.simon.school.model.dto.StudentDto;
import com.simon.school.client.StudentClient;
import com.simon.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.simon.school.utils.Utility.getObjectMapper;

@Service
@RequiredArgsConstructor
class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;
    private ObjectMapper objectMapper=getObjectMapper();

    public void saveSchool(SchoolDto schoolDto){
        School school= objectMapper.convertValue(schoolDto,School.class);
        schoolRepository.save(school);
    }

    public List<SchoolDto> findAllSchools(){
        List<School> schools=schoolRepository.findAll();
        List<SchoolDto>schoolDtos=objectMapper.convertValue(schools,new TypeReference<List<SchoolDto>>() {});
        return schoolDtos;
    }

    public FullSchoolResponse findSchoolWithStudents(Long schoolId) {
        School school=schoolRepository.findById(schoolId).orElseThrow();
        List<StudentDto> studentDtos =studentClient.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(studentDtos)
                .build();
    }
}