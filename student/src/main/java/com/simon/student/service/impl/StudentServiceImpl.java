package com.simon.student.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.student.model.dto.StudentDto;
import com.simon.student.model.entity.Student;
import com.simon.student.repository.StudentRepository;
import com.simon.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.simon.student.utils.Utility.getObjectMapper;

@Service
@RequiredArgsConstructor
class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private ObjectMapper objectMapper=getObjectMapper();

    public void saveStudent(StudentDto studentDto){
        Student student= objectMapper.convertValue(studentDto,Student.class);
        studentRepository.save(student);
    }

    public List<StudentDto> findAllStudents(){
        List<Student> students=studentRepository.findAll();
        List<StudentDto>studentDtos=objectMapper.convertValue(students,new TypeReference<List<StudentDto>>() {});
        return studentDtos;
    }

    public List<StudentDto> findAllStudentsBySchool(Long schoolId) {
        List<Student> students=studentRepository.findAllBySchoolId(schoolId);
        List<StudentDto>studentDtos=objectMapper.convertValue(students,new TypeReference<List<StudentDto>>() {});
        return studentDtos;
    }
}