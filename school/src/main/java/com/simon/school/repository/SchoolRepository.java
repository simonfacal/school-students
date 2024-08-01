package com.simon.school.repository;

import com.simon.school.model.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Long> {
}
