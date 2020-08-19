package com.bjss.domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class CompanyHistoryTest {

    @Test
    public void buildCompanyHistory() {
        CompanyHistory companyHistory = CompanyHistory.builder()
                .companyName("companyName")
                .startDate(LocalDate.MIN)
                .endDate(LocalDate.MAX)
                .build();

        assertEquals("companyName", companyHistory.getCompanyName());
        assertEquals(LocalDate.MIN, companyHistory.getStartDate());
        assertEquals(LocalDate.MAX, companyHistory.getEndDate());
    }

    @Test
    public void constructCompanyHistory() {
        CompanyHistory companyHistory = new CompanyHistory("companyName", LocalDate.MIN, LocalDate.MAX);

        assertEquals("companyName", companyHistory.getCompanyName());
        assertEquals(LocalDate.MIN, companyHistory.getStartDate());
        assertEquals(LocalDate.MAX, companyHistory.getEndDate());
    }
}