package com.simon.student.repository;

import com.simon.student.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllBySchoolId(Long schoolId);
}
