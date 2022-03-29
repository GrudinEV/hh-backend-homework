package ru.hh.school.converter;

import lombok.RequiredArgsConstructor;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.FavoriteEmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.enums.Popularity;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
@RequiredArgsConstructor
public class EmployerConverter {
    private static final String LIMIT_REGULAR = "limitPopularity";
    private final FileSettings fileSettings;
    private final AreaConverter areaConverter;

    public FavoriteEmployerDto toResponse(Employer employer) {
        return FavoriteEmployerDto.builder()
                .id(employer.getId())
                .name(employer.getName())
                .dateCreate(employer.getDateCreate())
                .description(employer.getDescription())
                .area(areaConverter.toResponse(employer.getArea()))
                .comment(employer.getComment())
                .popularity(employer.getViewsCount() > fileSettings.getInteger(LIMIT_REGULAR) ?
                        Popularity.POPULAR : Popularity.REGULAR)
                .viewsCount(employer.getViewsCount())
                .build();
    }

    public Employer dtoToEntity(EmployerDto employerDto, Area area, String comment) {
        return Employer.builder()
                .id(employerDto.getId())
                .name(employerDto.getName())
                .dateCreate(LocalDate.now())
                .description(employerDto.getDescription())
                .area(area)
                .comment(comment)
                .build();
    }
}
