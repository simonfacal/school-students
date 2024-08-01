package com.simon.school.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;
import com.simon.school.model.dto.StudentDto;
import com.simon.school.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.simon.school.utils.TestEntityFactory.SCHOOL_ID;
import static com.simon.school.utils.TestEntityFactory.getFullSchoolResponse;
import static com.simon.school.utils.TestEntityFactory.getSchoolDto;
import static com.simon.school.utils.TestEntityFactory.getSchoolDtos;
import static com.simon.school.utils.TestEntityFactory.getStudentDtos;
import static com.simon.school.utils.Utility.getObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SchoolController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SchoolControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper=getObjectMapper();
    @MockBean
    private SchoolService schoolService;

    @Test
    void saveSchool() throws Exception {
        SchoolDto schoolDto=getSchoolDto();
        mockMvc.perform(post("/api/v1/schools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schoolDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void findSchoolWithStudents() throws Exception {
        List<SchoolDto> schoolDtos=getSchoolDtos();
        when(schoolService.findAllSchools()).thenReturn(schoolDtos);
        mockMvc.perform(get("/api/v1/schools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(schoolDtos.size()));
    }

    @Test
    void testFindSchoolWithStudents() throws Exception {
        Long schoolId = SCHOOL_ID;
        List<StudentDto> studentDtos=getStudentDtos();
        FullSchoolResponse fullSchoolResponse=getFullSchoolResponse();
        when(schoolService.findSchoolWithStudents(any(Long.class))).thenReturn(fullSchoolResponse);

        mockMvc.perform(get("/api/v1/schools/with-students/{school-id}", schoolId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(fullSchoolResponse.getName()))
                .andExpect(jsonPath("$.email").value(fullSchoolResponse.getEmail()))
                .andExpect(jsonPath("$.students").isArray())
                .andExpect(jsonPath("$.students.length()").value(studentDtos.size()));
    }
}