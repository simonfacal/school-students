package com.simon.student.controller;

import com.simon.student.documentation.StudentApi;
import com.simon.student.model.dto.StudentDto;
import com.simon.student.service.StudentService;
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
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController implements StudentApi {
    private final StudentService studentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void saveStudent(@RequestBody StudentDto studentDto){

        studentService.saveStudent(studentDto);
    }
    @GetMapping
    @Override
    public ResponseEntity<List<StudentDto>> findAllStudents(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/school/{school-id}")
    @Override
    public ResponseEntity<List<StudentDto>> findAllStudentsBySchool(@PathVariable("school-id")Long schoolId){
        return ResponseEntity.ok(studentService.findAllStudentsBySchool(schoolId));
    }


}
