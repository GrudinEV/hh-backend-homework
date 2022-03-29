package ru.hh.school.dto.employer;

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
    private LocalDate dateCreate;
    private String description;
    private AreaDto area;
    private String comment;
    private Popularity popularity;
    private int viewsCount;
}
