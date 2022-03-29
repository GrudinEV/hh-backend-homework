package ru.hh.school.service;

import lombok.RequiredArgsConstructor;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.EmployerConverter;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.FavoriteEmployerDto;
import ru.hh.school.entity.Area;
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
    private final FavoriteEmployerDao employerDao;
    private final AreaDao areaDao;
    private final EmployerConverter converter;

    public boolean addEmployerInFavorites(long employerId, String comment) {
        Employer employer = employerDao.getEmployer(employerId);
        if (employer == null) {
            EmployerDto employerDto = client.getEmployer(employerId);
            Area area = areaDao.getArea(employerDto.getArea().getId());
            if (area == null) {
                area = new Area(employerDto.getArea().getId(), employerDto.getArea().getName());
            }
            employer = converter.dtoToEntity(employerDto, area, comment);
            employerDao.save(employer);
            return true;
        }
        return false;
    }

    public List<FavoriteEmployerDto> getFavoriteEmployers(int page, int perPage) {
        List<Employer> employers = employerDao.getEmployers(page, perPage);
        return employers.stream()
                .peek(employer -> employer.setViewsCount(employer.getViewsCount() + 1))
                .peek(employerDao::save)
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    public boolean updateEmployer(long employerId, String comment) {
        Employer employer = employerDao.getEmployer(employerId);
        if (employer != null) {
            employer.setComment(comment);
            employerDao.save(employer);
            return true;
        }
        return false;
    }

    public boolean deleteEmployer(long employerId) {
        Employer employer = employerDao.getEmployer(employerId);
        if (employer != null) {
            employerDao.delete(employer);
            return true;
        }
        return false;
    }

    public void refreshEmployer(long employerId) {
        deleteEmployer(employerId);
        addEmployerInFavorites(employerId, "");
    }
}
