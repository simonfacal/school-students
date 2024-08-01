package com.simon.student.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.student.model.dto.StudentDto;
import com.simon.student.service.StudentService;
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

import static com.simon.student.utils.TestEntityFactory.SCHOOL_ID;
import static com.simon.student.utils.TestEntityFactory.getStudentDto;
import static com.simon.student.utils.TestEntityFactory.getStudentDtos;
import static com.simon.student.utils.Utility.getObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper=getObjectMapper();
    @MockBean
    private StudentService studentService;

    @Test
    void saveStudentTest() throws Exception {
        StudentDto studentDto=getStudentDto();
        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void findAllStudentsBySchoolTest() throws Exception {

        List<StudentDto>studentDtos=getStudentDtos();

        when(studentService.findAllStudents()).thenReturn(studentDtos);

        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(studentDtos.size()));
    }

    @Test
    void testFindAllStudentsBySchoolTest() throws Exception {
        Long schoolId = SCHOOL_ID;
        List<StudentDto>studentDtos=getStudentDtos();

        when(studentService.findAllStudentsBySchool(schoolId)).thenReturn(studentDtos);

        mockMvc.perform(get("/api/v1/students/school/{school-id}", schoolId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(studentDtos.size()));
    }
}