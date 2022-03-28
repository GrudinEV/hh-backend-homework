package ru.hh.school.dto.employer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hh.school.entity.Area;
import ru.hh.school.enums.Popularity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEmployerDto {
    private long id;
    private String name;
    private LocalDate dateCreate;
    private String description;
    private Area area;
    private String comment;
    private Popularity popularity;
    private int viewsCount;
}
