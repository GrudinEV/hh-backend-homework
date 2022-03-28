package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.EmployerConverter;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.resource.employer.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.resource.employer.FavoriteEmployerResource;
import ru.hh.school.resource.vacancy.VacancyResource;
import ru.hh.school.service.FavoriteEmployerService;

@Configuration
@Import({
  // import your beans here
        VacancyResource.class,
        FavoriteEmployerResource.class,
        FavoriteEmployerService.class,
        FavoriteEmployerDao.class,
        EmployerConverter.class,
        HttpClient.class,
        EmployerResource.class,
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
