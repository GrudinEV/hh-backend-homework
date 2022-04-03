package ru.hh.school.resource.vacancy;

import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.vacancy.FavoriteVacancyDto;
import ru.hh.school.dto.vacancy.VacancyAddRequestDto;
import ru.hh.school.service.FavoriteVacancyService;
import ru.hh.school.validate.MyValidator;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/vacancy")
@RequiredArgsConstructor
public class FavoriteVacancyResource {
    private final Logger logger = LoggerFactory.getLogger(FavoriteVacancyResource.class);
    private final FavoriteVacancyService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addVacancyInFavorites(VacancyAddRequestDto request) {
        MyValidator.validateId(request.getVacancyId());
        logger.info(String.format("Start add vacancy in favorites with: id=%d", request.getVacancyId()));
        if (service.addVacancyInFavorites(request.getVacancyId(), request.getComment())) {
            logger.info(String.format("Vacancy with id=%d added in favorites.", request.getVacancyId()));
            return Response.ok(String.format("Vacancy with id=%d added in favorites.",
                            request.getVacancyId()))
                    .build();
        } else {
            logger.info(String.format("Vacancy with id=%d already in favorites", request.getVacancyId()));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                            String.format("Vacancy with id=%d already in favorites", request.getVacancyId()))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavoriteVacancyDto> getFavoriteVacancy(@QueryParam("page") @DefaultValue("0") @Min(value = 0) int page,
                                                         @QueryParam("per_page") @DefaultValue("20") @Valid @Min(1) @Max(100) int perPage) {
        MyValidator.validatePaginationProperties(page, perPage);
        logger.info("Start getting favorite vacancies");
        return service.getFavoriteVacancies(page, perPage);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{vacancy_id}/refresh")
    public Response refreshVacancy(@PathParam("vacancy_id") long vacancyId) {
        MyValidator.validateId(vacancyId);
        logger.info(String.format("Start refreshing favorite vacancy with id=%d", vacancyId));
        if (service.refreshVacancy(vacancyId)) {
            logger.info(String.format("Vacancy with id=%d refreshed.", vacancyId));
            return Response.ok(String.format("Vacancy with id=%d refreshed.", vacancyId))
                    .build();
        } else {
            logger.info(String.format("Vacancy with id=%d not exists", vacancyId));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                            String.format("Vacancy with id=%d not exists", vacancyId))
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{vacancy_id}")
    public Response deleteVacancy(@PathParam("vacancy_id") long vacancyId) {
        MyValidator.validateId(vacancyId);
        logger.info(String.format("Start deleting favorite vacancy with id=%d", vacancyId));
        if (service.deleteVacancy(vacancyId)) {
            logger.info(String.format("Vacancy with id=%d deleted.", vacancyId));
            return Response.ok(String.format("Vacancy with id=%d deleted.", vacancyId))
                    .build();
        } else {
            logger.info(String.format("Vacancy with id=%d not exists", vacancyId));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                            String.format("Vacancy with id=%d not exists", vacancyId))
                    .build();
        }
    }
}
