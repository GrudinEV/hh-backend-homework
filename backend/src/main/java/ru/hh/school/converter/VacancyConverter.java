package ru.hh.school.converter;

import lombok.RequiredArgsConstructor;
import ru.hh.school.dto.vacancy.ShortVacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Singleton
@RequiredArgsConstructor
public class VacancyConverter {
    public Vacancy dtoToEntity(ShortVacancyDto vacancyDto, Employer employer, Area vacancyArea, String comment) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .name(vacancyDto.getName())
                .dateCreate(LocalDate.now())
                .area(vacancyArea)
                .salaryFrom(vacancyDto.getSalary().getFrom())
                .salaryTo(vacancyDto.getSalary().getTo())
                .salaryCurrency(vacancyDto.getSalary().getCurrency())
                .salaryGross(vacancyDto.getSalary().isGross())
                .createdAt(LocalDateTime.parse(vacancyDto.getCreatedAt(), dtf))
                .employer(employer)
                .viewsCount(0)
                .comment(comment)
                .build();
    }
}
