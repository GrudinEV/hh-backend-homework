package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.employer.ShortEmployerDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class VacancyFromApiDto {
    private long id;
    private String name;
    private AreaDto area;
    private SalaryDto salary;
    @JsonProperty("published_at")
    private String createdAt;
    private ShortEmployerDto employer;
}
