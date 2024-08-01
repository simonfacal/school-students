package com.simon.school.documentation;

import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;
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

public interface SchoolApi {
    @Operation(
            description = "Creates a school",
            summary = "Creates a school"
    )
    @ApiResponses(value = {
            @ApiResponse(description = "School created", responseCode = "201")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSchool(@RequestBody SchoolDto schoolDto);
    @Operation(
            description = "Obtains all schools",
            summary = "Obtains all schools"
    )
    @ApiResponses(value = {
            @ApiResponse(description = "", responseCode = "200")
    })
    @GetMapping
    public ResponseEntity<List<SchoolDto>> findAllSchools();

    @Operation(
            description = "Obtains a school by the schoolId with all the students",
            summary = "Obtains school with students"
    )
    @ApiResponses(value = {
            @ApiResponse(description = "", responseCode = "200")
    })
    @GetMapping("with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudents(@PathVariable("school-id") Long schoolId);

}
