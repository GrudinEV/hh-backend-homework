package ru.hh.school.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.entity.Area;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class AreaDao {
    private final SessionFactory sessionFactory;

    public Area getArea(long areaId) {
        Query<Area> areaQuery = sessionFactory.getCurrentSession()
                .createQuery("from Area area where area.id = :id", Area.class)
                .setParameter("id", areaId);
        if (areaQuery.list().isEmpty()) {
            return null;
        }
        return areaQuery.getSingleResult();
    }
}
