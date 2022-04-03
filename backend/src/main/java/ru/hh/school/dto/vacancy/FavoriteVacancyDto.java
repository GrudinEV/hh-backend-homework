package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.FavoriteEmployerDto;
import ru.hh.school.enums.Popularity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteVacancyDto {
    private long id;
    private String name;
    @JsonProperty(value = "date_create")
    private String dateCreate;
    private AreaDto area;
    @JsonProperty(value = "salary")
    private SalaryDto salaryDto;
    @JsonProperty(value = "created_at")
    private String createdAt;
    private FavoriteEmployerDto employer;
    private Popularity popularity;
    @JsonProperty(value = "views_count")
    private int viewsCount;
    private String comment;
}
