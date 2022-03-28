package ru.hh.school.service;

import lombok.RequiredArgsConstructor;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.EmployerConverter;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.FavoriteEmployerDto;
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

    public boolean addEmployerInFavorites(long employerId, String comment) {
        EmployerDto employerDto = client.getEmployer(employerId);
        return dao.addEmployerInFavorites(employerDto, comment);
    }

    public List<FavoriteEmployerDto> getFavoriteEmployers(int page, int perPage) {
        List<Employer> employers = dao.getFavoriteEmployers(page, perPage);
        return employers.stream()
                .peek(employer -> employer.setViewsCount(employer.getViewsCount() + 1))
                .peek(dao::save)
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    public boolean updateEmployer(long employerId, String comment) {
        Employer employer = dao.getFavoriteEmployer(employerId);
        if (employer != null) {
            employer.setComment(comment);
            dao.save(employer);
            return true;
        }
        return false;
    }

    public boolean deleteEmployer(long employerId) {
        Employer employer = dao.getFavoriteEmployer(employerId);
        if (employer != null) {
            dao.delete(employer);
            return true;
        }
        return false;
    }

    public void refreshEmployer(long employerId) {
        deleteEmployer(employerId);
        addEmployerInFavorites(employerId, "");
    }
}
