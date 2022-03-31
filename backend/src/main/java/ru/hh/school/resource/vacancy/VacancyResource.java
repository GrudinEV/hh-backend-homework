package ru.hh.school.resource.vacancy;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.client.HttpClient;
import ru.hh.school.converter.VacancyConverter;
import ru.hh.school.dto.vacancy.VacancyFromApiDto;
import ru.hh.school.dto.vacancy.VacanciesResponseDto;
import ru.hh.school.dto.vacancy.VacancyResponseDto;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/vacancy")
@RequiredArgsConstructor
public class VacancyResource {
    private final Logger logger = LoggerFactory.getLogger(VacancyResource.class);
    private final HttpClient client;
    private final VacancyConverter converter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacancyResponseDto> getVacancies(@QueryParam("page") @DefaultValue("0") @Min(value = 0) int page,
                                                 @QueryParam("per_page") @DefaultValue("20") @Valid @Min(1) @Max(100) int perPage,
                                                 @QueryParam("query") String query) {
        logger.info(String.format("Start search vacancy with: page=%s, size=%s, text=%s",
                page, perPage, query));
        return client.getVacancies(page, perPage, query).getItems().stream()
                .map(converter::fromApiToResponseDto)
                .collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{vacancy_id}")
    public VacancyResponseDto getVacancy(@PathParam("vacancy_id") long vacancyId) {
        logger.info(String.format("Start search vacancy with: id=%d", vacancyId));
        return converter.fromApiToResponseDto(client.getVacancy(vacancyId));
    }
}
