package com.simon.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullSchoolResponse {
    private String name;
    private String email;
    private List<StudentDto> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullSchoolResponse that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, students);
    }
}
