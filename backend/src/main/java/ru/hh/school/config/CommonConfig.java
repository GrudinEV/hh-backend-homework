package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.AreaConverter;
import ru.hh.school.converter.EmployerConverter;
import ru.hh.school.converter.VacancyConverter;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dao.FavoriteVacancyDao;
import ru.hh.school.resource.employer.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.resource.employer.FavoriteEmployerResource;
import ru.hh.school.resource.vacancy.FavoriteVacancyResource;
import ru.hh.school.resource.vacancy.VacancyResource;
import ru.hh.school.service.FavoriteEmployerService;
import ru.hh.school.service.FavoriteVacancyService;

@Configuration
@Import({
  // import your beans here
        FavoriteVacancyResource.class,
        FavoriteVacancyService.class,
        FavoriteVacancyDao.class,
        VacancyConverter.class,
        VacancyResource.class,
        FavoriteEmployerResource.class,
        FavoriteEmployerService.class,
        FavoriteEmployerDao.class,
        EmployerConverter.class,
        EmployerResource.class,
        AreaDao.class,
        AreaConverter.class,
        HttpClient.class,
  ExampleResource.class,
  NabCommonConfig.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }

}
