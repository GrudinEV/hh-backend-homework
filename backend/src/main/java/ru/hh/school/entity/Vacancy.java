package ru.hh.school.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {
    @Id
    private long id;
    private String name;
    @Column(name = "date_create")
    private LocalDate dateCreate;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;
    @Column(name = "salary_from")
    private Integer salaryFrom;
    @Column(name = "salary_to")
    private Integer salaryTo;
    @Column(name = "salary_currency")
    private String salaryCurrency;
    @Column(name = "salary_gross")
    private boolean salaryGross;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;
    @Column(name = "views_count")
    private int viewsCount;
    private String comment;
}
