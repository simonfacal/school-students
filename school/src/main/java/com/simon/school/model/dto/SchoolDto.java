package com.simon.school.model.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolDto {
    @NotEmpty(message="name must not be empty")
    private String name;
    @NotEmpty(message="email must not be empty")
    @Pattern(regexp = ".*@.*", message = "email must contain the character '@'")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolDto schoolDto)) return false;
        return Objects.equals(name, schoolDto.name) && Objects.equals(email, schoolDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
