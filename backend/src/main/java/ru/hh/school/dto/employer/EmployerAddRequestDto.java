package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerAddRequestDto {
    @JsonProperty("employer_id")
    private long employerId;
    private String comment;
}
