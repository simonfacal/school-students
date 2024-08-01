package com.simon.school.controller;

import com.simon.school.documentation.SchoolApi;
import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;
import com.simon.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController implements SchoolApi {
    private final SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void saveSchool(@RequestBody SchoolDto schoolDto){
        schoolService.saveSchool(schoolDto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<SchoolDto>> findAllSchools(){
        return ResponseEntity.ok(schoolService.findAllSchools());
    }

    @GetMapping("with-students/{school-id}")
    @Override
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudents(@PathVariable("school-id") Long schoolId){
        return ResponseEntity.ok(schoolService.findSchoolWithStudents(schoolId));
    }



}
