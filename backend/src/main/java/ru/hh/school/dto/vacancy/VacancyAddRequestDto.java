package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacancyAddRequestDto {
    @JsonProperty("vacancy_id")
    private long vacancyId;
    private String comment;
}
