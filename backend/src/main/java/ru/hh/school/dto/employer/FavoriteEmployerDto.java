package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.enums.Popularity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteEmployerDto {
    private long id;
    private String name;
    @JsonProperty(value = "date_create")
    private String dateCreate;
    private String description;
    private AreaDto area;
    private String comment;
    private Popularity popularity;
    @JsonProperty(value = "views_count")
    private int viewsCount;
}
