package ru.hh.school.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class FavoriteEmployerDao {
    private final SessionFactory sessionFactory;
    public boolean addEmployerInFavorites(EmployerDto employerDto, String comment) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employer> employerQuery = session
                .createQuery("from Employer employer where employer.id = :id", Employer.class)
                .setParameter("id", employerDto.getId());
        if (employerQuery.list().isEmpty()) {
            Query<Area> areaQuery = session.createQuery("from Area area where area.id = :id", Area.class)
                    .setParameter("id", employerDto.getArea().getId());
            Area area = null;
            if (areaQuery.list().isEmpty()) {
                area = new Area(employerDto.getArea().getId(),
                        employerDto.getArea().getName());
            } else {
                area = areaQuery.getSingleResult();
            }
            Employer employer = new Employer(
                    employerDto.getId(),
                    employerDto.getName(),
                    LocalDate.now(),
                    employerDto.getDescription(),
                    area,
                    comment,
                    0
            );
            session.save(employer);
            return true;
        } else {
            return false;
        }
    }

    public List<Employer> getFavoriteEmployers(int page, int perPage) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employer", Employer.class)
                .setFirstResult(page * perPage)
                .setMaxResults(perPage)
                .getResultList();
    }

    public void save(Employer employer) {
        sessionFactory.getCurrentSession()
                .save(employer);
    }

    public Employer getFavoriteEmployer(long employerId) {
        Query<Employer> employerQuery = sessionFactory.getCurrentSession()
                .createQuery("from Employer employer where employer.id = :id", Employer.class)
                .setParameter("id", employerId);
        if (!employerQuery.list().isEmpty()) {
            return employerQuery.getSingleResult();
        }
        return null;
    }

    public void delete(Employer employer) {
        sessionFactory.getCurrentSession()
                .delete(employer);
    }
}
