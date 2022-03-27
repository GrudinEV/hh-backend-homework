package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployersResponseDto {
    private List<ShortEmployerDto> items;

    public List<ShortEmployerDto> getItems() {
        return items;
    }

    public void setItems(List<ShortEmployerDto> items) {
        this.items = items;
    }
}
