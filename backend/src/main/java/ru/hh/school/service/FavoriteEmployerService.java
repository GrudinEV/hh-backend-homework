package ru.hh.school.service;

import lombok.RequiredArgsConstructor;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.EmployerConverter;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Employer;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Transactional
@RequiredArgsConstructor
public class FavoriteEmployerService {

    private final HttpClient client;
    private final FavoriteEmployerDao dao;
    private final EmployerConverter converter;

    public boolean addEmployer(long employerId, String comment) {
        EmployerDto employerDto = client.getEmployer(employerId);
        return dao.addEmployerInFavorites(employerDto, comment);
    }

    public List<FavoriteEmployerDto> getFavoriteEmployers() {
        List<Employer> employers = dao.getFavoriteEmployers();
        return employers.stream()
                .peek(employer -> employer.setViewsCount(employer.getViewsCount() + 1))
                .peek(dao::save)
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }
}
