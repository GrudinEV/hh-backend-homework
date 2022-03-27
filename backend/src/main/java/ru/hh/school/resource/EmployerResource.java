package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.client.HttpClient;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployersResponseDto;
import ru.hh.school.dto.ShortEmployerDto;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/employer")
public class EmployerResource {
    private final Logger logger = LoggerFactory.getLogger(EmployerResource.class);
    private final HttpClient client;

    public EmployerResource(HttpClient client/*, EmployerService service*/) {
        this.client = client;
//        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShortEmployerDto> getEmployers(@QueryParam("page") @DefaultValue("0") @Min(value = 0) int page,
                                               @QueryParam("per_page") @DefaultValue("20") @Valid @Min(1) @Max(100) int perPage,
                                               @QueryParam("query") String query) {
        logger.info(String.format("Start search employers with: page=%s, size=%s, text=%s",
                page, perPage, query));
        EmployersResponseDto employers = client.getEmployers(page, perPage, query);
        return employers.getItems();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employer_id}")
    public EmployerDto getEmployer(@PathParam("employer_id") long employerId) {
        logger.info(String.format("Start search employer with: id=%d", employerId));
        return client.getEmployer(employerId);
    }
}
