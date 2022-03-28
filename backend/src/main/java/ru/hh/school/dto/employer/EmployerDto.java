package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.hh.school.dto.AreaDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class EmployerDto {
    private long id;
    private String name;
    private String description;
    private AreaDto area;
}
