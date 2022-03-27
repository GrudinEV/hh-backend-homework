package ru.hh.school.client;

import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployersResponseDto;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Singleton
public class HttpClient {
    private static final String URI = "https://api.hh.ru";
    private static final String EMPLOYERS_PATH = "employers";
    private static final String HEADER_AGENT_PARAM = "HH-User-Agent";
    private static final String HEADER_AGENT = "HH-School-homework-app (grudinev@gmail.com)";
    private final Client client = ClientBuilder.newClient();

    public EmployersResponseDto getEmployers(int page, int perPage, String text) {
        return client
                .target(URI)
                .path(EMPLOYERS_PATH)
                .queryParam("page", String.valueOf(page))
                .queryParam("per_page", String.valueOf(perPage))
                .queryParam("text", text)
                .request()
                .header(HEADER_AGENT_PARAM, HEADER_AGENT)
                .get(EmployersResponseDto.class);
    }

    public EmployerDto getEmployer(long employerId) {
        StringBuilder sb = new StringBuilder(EMPLOYERS_PATH);
        sb.append("/");
        sb.append(employerId);
        return client
                .target(URI)
                .path(sb.toString())
                .request()
                .header(HEADER_AGENT_PARAM, HEADER_AGENT)
                .get(EmployerDto.class);
    }
}
