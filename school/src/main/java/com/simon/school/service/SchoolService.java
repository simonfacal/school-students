package com.simon.school.service;

import com.simon.school.model.dto.FullSchoolResponse;
import com.simon.school.model.dto.SchoolDto;

import java.util.List;

public interface SchoolService {
    public void saveSchool(SchoolDto schoolDto);

    public List<SchoolDto> findAllSchools();

    public FullSchoolResponse findSchoolWithStudents(Long schoolId);
}
