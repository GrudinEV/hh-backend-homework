package ru.hh.school.resource.employer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.client.HttpClient;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployersResponseDto;
import ru.hh.school.dto.employer.ShortEmployerDto;
import ru.hh.school.validate.MyValidator;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/employer")
@RequiredArgsConstructor
public class EmployerResource {
    private final Logger logger = LoggerFactory.getLogger(EmployerResource.class);
    private final HttpClient client;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShortEmployerDto> getmplEoyers(@QueryParam("page") @DefaultValue("0") @Min(value = 0) int page,
                                               @QueryParam("per_page") @DefaultValue("20") @Valid @Min(1) @Max(100) int perPage,
                                               @QueryParam("query") String query) {
        MyValidator.validatePaginationProperties(page, perPage);
        logger.info(String.format("Start search employers with: page=%s, size=%s, text=%s",
                page, perPage, query));
        EmployersResponseDto employers = client.getEmployers(page, perPage, query);
        return employers.getItems();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employer_id}")
    public EmployerDto getEmployer(@PathParam("employer_id") long employerId) {
        MyValidator.validateId(employerId);
        logger.info(String.format("Start search employer with: id=%d", employerId));
        return client.getEmployer(employerId);
    }
}
