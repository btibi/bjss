package com.bjss.domain;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class CompanyHistory {

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    private LocalDate endDate;
}
