package com.simon.student.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotEmpty(message="firstname must not be empty")
    private String firstname;
    @NotEmpty(message="lastname must not be empty")
    private String lastname;
    @Pattern(regexp = ".*@.*", message = "email must contain the character '@'")
    private String email;
    @NotNull(message="schoolId must not be null")
    @Positive(message="schoolId must not be negative")
    private Long schoolId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDto that)) return false;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(email, that.email) && Objects.equals(schoolId, that.schoolId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, schoolId);
    }
}
