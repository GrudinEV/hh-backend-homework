package ru.hh.school.dto.vacancy;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryDto {
    private Integer to;
    private Integer from;
    private String currency;
    private boolean gross;
}
