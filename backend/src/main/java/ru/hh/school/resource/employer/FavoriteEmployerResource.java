package ru.hh.school.resource.employer;

import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.employer.EmployerAddRequestDto;
import ru.hh.school.dto.employer.EmployerPutRequestDto;
import ru.hh.school.dto.employer.FavoriteEmployerDto;
import ru.hh.school.service.FavoriteEmployerService;
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
@Path("/favorites/employer")
@RequiredArgsConstructor
public class FavoriteEmployerResource {
    private final Logger logger = LoggerFactory.getLogger(FavoriteEmployerResource.class);
    private final FavoriteEmployerService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEmployerInFavorites(EmployerAddRequestDto request) {
        MyValidator.validateId(request.getEmployerId());
        logger.info(String.format("Start add employer in favorites with: id=%d", request.getEmployerId()));
        if (service.addEmployerInFavorites(request.getEmployerId(), request.getComment())) {
            logger.info(String.format("Employer with id=%d added in favorites.", request.getEmployerId()));
            return Response.ok(String.format("Employer with id=%d added in favorites.",
                    request.getEmployerId()))
                    .build();
        } else {
            logger.info(String.format("Employers with id=%d already in favorites", request.getEmployerId()));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                    String.format("Employers with id=%d already in favorites", request.getEmployerId()))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavoriteEmployerDto> getFavoriteEmployers(@QueryParam("page") @DefaultValue("0") @Min(value = 0) int page,
                                                          @QueryParam("per_page") @DefaultValue("20") @Valid @Min(1) @Max(100) int perPage) {
        MyValidator.validatePaginationProperties(page, perPage);
        logger.info("Start getting favorite employers");
        return service.getFavoriteEmployers(page, perPage);
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{employer_id}")
    public Response updateEmployer(@PathParam("employer_id") long employerId,
                                   EmployerPutRequestDto request) {
        MyValidator.validateId(employerId);
        String comment = request.getComment();
        logger.info(String.format("Start updating favorite employers with id=%d, comment=%s",
                employerId, comment));
        if (service.updateEmployer(employerId, comment)) {
            logger.info(String.format("Employer with id=%d updated with comment=%s.",
                    employerId, comment));
            return Response.ok(String.format("Employer with id=%d updated with comment=%s.",
                            employerId, comment))
                    .build();
        } else {
            logger.info(String.format("Employers with id=%d not exists", employerId));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                            String.format("Employers with id=%d not exists", employerId))
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{employer_id}")
    public Response deleteEmployer(@PathParam("employer_id") long employerId) {
        MyValidator.validateId(employerId);
        logger.info(String.format("Start deleting favorite employers with id=%d", employerId));
        if (service.deleteEmployer(employerId)) {
            logger.info(String.format("Employer with id=%d deleted.", employerId));
            return Response.ok(String.format("Employer with id=%d deleted.", employerId))
                    .build();
        } else {
            logger.info(String.format("Employers with id=%d not exists", employerId));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                            String.format("Employers with id=%d not exists", employerId))
                    .build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{employer_id}/refresh")
    public void refreshEmployer(@PathParam("employer_id") long employerId) {
        MyValidator.validateId(employerId);
        logger.info(String.format("Start refreshing favorite employer with id=%d", employerId));
        service.refreshEmployer(employerId);
    }
}
