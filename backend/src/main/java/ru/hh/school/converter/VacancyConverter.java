package ru.hh.school.converter;

import lombok.RequiredArgsConstructor;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.vacancy.FavoriteVacancyDto;
import ru.hh.school.dto.vacancy.SalaryDto;
import ru.hh.school.dto.vacancy.ShortVacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.enums.Popularity;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Singleton
@RequiredArgsConstructor
public class VacancyConverter {
    private static final String LIMIT_REGULAR = "limitPopularity";
    private final FileSettings fileSettings;
    private final EmployerConverter employerConverter;
    private final AreaConverter areaConverter;
    public FavoriteVacancyDto toResponse(Vacancy vacancy) {
        return FavoriteVacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .dateCreate(vacancy.getDateCreate().toString())
                .area(areaConverter.toResponse(vacancy.getArea()))
                .salaryDto(vacancy.getSalaryFrom() == null && vacancy.getSalaryTo() == null && vacancy.getSalaryCurrency() == null ?
                        null :
                        SalaryDto.builder()
                                .from(vacancy.getSalaryFrom())
                                .to(vacancy.getSalaryTo())
                                .currency(vacancy.getSalaryCurrency())
                                .gross(vacancy.isSalaryGross())
                                .build())
                .createdAt(vacancy.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME))
                .employer(employerConverter.toResponse(vacancy.getEmployer()))
                .popularity(vacancy.getViewsCount() > fileSettings.getInteger(LIMIT_REGULAR) ?
                        Popularity.POPULAR : Popularity.REGULAR)
                .viewsCount(vacancy.getViewsCount())
                .comment(vacancy.getComment())
                .build();
    }

    public Vacancy dtoToEntity(ShortVacancyDto vacancyDto, Employer employer, Area vacancyArea, String comment) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .name(vacancyDto.getName())
                .dateCreate(LocalDate.now())
                .area(vacancyArea)
                .salaryFrom(vacancyDto.getSalary() == null ? null : vacancyDto.getSalary().getFrom())
                .salaryTo(vacancyDto.getSalary() == null ? null : vacancyDto.getSalary().getTo())
                .salaryCurrency(vacancyDto.getSalary() == null ? null : vacancyDto.getSalary().getCurrency())
                .salaryGross(vacancyDto.getSalary() != null && vacancyDto.getSalary().isGross())
                .createdAt(LocalDateTime.parse(vacancyDto.getCreatedAt(), dtf))
                .employer(employer)
                .viewsCount(0)
                .comment(comment)
                .build();
    }
}
