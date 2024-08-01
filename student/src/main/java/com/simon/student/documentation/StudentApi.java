package com.simon.student.documentation;

import com.simon.student.model.dto.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface StudentApi {
    @Operation(
            description = "Creates a student",
            summary = "Creates a student"
    )
    @ApiResponses(value = {
            @ApiResponse(description = "Student created", responseCode = "201")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody StudentDto studentDto);
    @Operation(
            description = "Obtains all students",
            summary = "Obtains all students"
    )
    @ApiResponses(value = {
            @ApiResponse(description = "", responseCode = "200")
    })
    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudents();
    @Operation(
            description = "Obtains all students from a given school by the school id",
            summary = "Obtains all students from a school"
    )
    @ApiResponses(value = {
            @ApiResponse(description = "", responseCode = "200")
    })
    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<StudentDto>> findAllStudentsBySchool(@PathVariable("school-id")Long schoolId);

}
