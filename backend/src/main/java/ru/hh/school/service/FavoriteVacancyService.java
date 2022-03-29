package ru.hh.school.service;

import lombok.RequiredArgsConstructor;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.EmployerConverter;
import ru.hh.school.converter.VacancyConverter;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dao.FavoriteVacancyDao;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.vacancy.ShortVacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
@Transactional
@RequiredArgsConstructor
public class FavoriteVacancyService {
    private final HttpClient client;
    private final FavoriteVacancyDao vacancyDao;
    private final FavoriteEmployerDao employerDao;
    private final AreaDao areaDao;
    private final EmployerConverter employerConverter;
    private final VacancyConverter vacancyConverter;
    public boolean addVacancyInFavorites(long vacancyId, String comment) {
        Vacancy vacancy = vacancyDao.getVacancy(vacancyId);
        if (vacancy == null) {
            ShortVacancyDto vacancyDto = client.getVacancy(vacancyId);
            Area vacancyArea = areaDao.getArea(vacancyDto.getArea().getId());
            if (vacancyArea == null) {
                vacancyArea = new Area(vacancyDto.getArea().getId(), vacancyDto.getArea().getName());
            }
            Employer employer = employerDao.getEmployer(vacancyDto.getEmployer().getId());
            if (employer == null) {
                EmployerDto employerDto = client.getEmployer(vacancyDto.getEmployer().getId());
                Area employerArea = areaDao.getArea(employerDto.getArea().getId());
                if (employerArea == null) {
                    employerArea = new Area(employerDto.getArea().getId(), employerDto.getArea().getName());
                }
                employer = employerConverter.dtoToEntity(employerDto, employerArea, "");
            }
            vacancy = vacancyConverter.dtoToEntity(vacancyDto, employer, vacancyArea, comment);
            vacancyDao.save(vacancy);
            return true;
        }
        return false;
    }

    public boolean deleteVacancy(long vacancyId) {
        Vacancy vacancy = vacancyDao.getVacancy(vacancyId);
        if (vacancy != null) {
            vacancyDao.delete(vacancy);
            return true;
        }
        return false;
    }
}
