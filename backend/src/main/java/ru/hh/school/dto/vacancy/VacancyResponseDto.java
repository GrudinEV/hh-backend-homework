package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.employer.ShortEmployerDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyResponseDto {
    private long id;
    private String name;
    private AreaDto area;
    private SalaryDto salary;
    @JsonProperty("created_at")
    private String createdAt;
    private ShortEmployerDto employer;
}
