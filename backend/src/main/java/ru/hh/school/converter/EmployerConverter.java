package ru.hh.school.converter;

import lombok.RequiredArgsConstructor;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.enums.Popularity;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class EmployerConverter {
    private static final String LIMIT_REGULAR = "limitPopularity";
    private final FileSettings fileSettings;

    public FavoriteEmployerDto toResponse(Employer employer) {
        return new FavoriteEmployerDto(
                employer.getId(),
                employer.getName(),
                employer.getDateCreate(),
                employer.getDescription(),
                employer.getArea(),
                employer.getComment(),
                employer.getViewsCount() > fileSettings.getInteger(LIMIT_REGULAR) ? Popularity.POPULAR : Popularity.REGULAR,
                employer.getViewsCount()
        );
    }
}
