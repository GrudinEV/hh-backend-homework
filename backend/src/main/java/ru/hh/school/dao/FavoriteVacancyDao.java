package ru.hh.school.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class FavoriteVacancyDao {
    private final SessionFactory sessionFactory;

    public Vacancy getVacancy(long vacancyId) {
        Query<Vacancy> vacancyQuery = sessionFactory.getCurrentSession()
                .createQuery("from Vacancy vacancy where vacancy.id = :id", Vacancy.class)
                .setParameter("id", vacancyId);
        if (!vacancyQuery.list().isEmpty()) {
            return vacancyQuery.getSingleResult();
        }
        return null;
    }

    public void save(Vacancy vacancy) {
        sessionFactory.getCurrentSession()
                .save(vacancy);
    }

    public void delete(Vacancy vacancy) {
        vacancy.setEmployer(null);
        vacancy.setArea(null);
        sessionFactory.getCurrentSession()
                .delete(vacancy);
    }

    public List<Vacancy> getVacancies(int page, int perPage) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Vacancy", Vacancy.class)
                .setFirstResult(page * perPage)
                .setMaxResults(perPage)
                .getResultList();
    }
}
