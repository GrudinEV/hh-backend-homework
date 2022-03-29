package ru.hh.school.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.entity.Employer;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class FavoriteEmployerDao {
    private final SessionFactory sessionFactory;

    public Employer getEmployer(long employerId) {
        Query<Employer> employerQuery = sessionFactory.getCurrentSession()
                .createQuery("from Employer employer where employer.id = :id", Employer.class)
                .setParameter("id", employerId);
        if (!employerQuery.list().isEmpty()) {
            return employerQuery.getSingleResult();
        }
        return null;
    }

    public List<Employer> getEmployers(int page, int perPage) {
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

    public void delete(Employer employer) {
        sessionFactory.getCurrentSession()
                .delete(employer);
    }
}
