package ru.hh.school.dto.vacancy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryDto {
    private Integer to;
    private Integer from;
    private String currency;
    private boolean gross;
}
