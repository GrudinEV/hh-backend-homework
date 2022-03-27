package ru.hh.school.resource;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.EmployerRequestDto;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.service.FavoriteEmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/employer")
public class FavoriteEmployerResource {
    private final Logger logger = LoggerFactory.getLogger(FavoriteEmployerResource.class);
    private final FavoriteEmployerService service;

    public FavoriteEmployerResource(FavoriteEmployerService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEmployerInFavorites(EmployerRequestDto request) {
        logger.info(String.format("Start add employer in favorites with: id=%d", request.getEmployerId()));
        if (service.addEmployer(request.getEmployerId(), request.getComment())) {
            logger.info(String.format("Employer with id=%d added in favorites.", request.getEmployerId()));
            return Response.ok(String.format("Employer with id=%d added in favorites.",
                    request.getEmployerId()))
                    .build();
        } else {
            logger.info(String.format("Employers with id=%d already in favorites", request.getEmployerId()));
            return Response.status(HttpStatus.BAD_REQUEST_400,
                    String.format("Employers with id=%d already in favorites",request.getEmployerId()))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavoriteEmployerDto> getFavoriteEmployers() {
        logger.info("Start getting favorite employers");
        return service.getFavoriteEmployers();
    }
}
