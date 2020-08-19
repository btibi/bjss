package com.bjss.domain;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CvTest {

    @Test
    public void buildCv() {
        Cv cv = Cv.builder()
                .cvId(1L)
                .name("name")
                .skills(asList("skill_1", "skill_2"))
                .companyHistories(emptyList())
                .build();

        assertEquals(1L, cv.getCvId());
        assertEquals("name", cv.getName());
        assertEquals(asList("skill_1", "skill_2"), cv.getSkills());
        assertTrue(cv.getCompanyHistories().isEmpty());
    }

    @Test
    public void constructCv() {
        Cv cv = new Cv(1L, "name", asList("skill_1", "skill_2"), emptyList());

        assertEquals(1L, cv.getCvId());
        assertEquals("name", cv.getName());
        assertEquals(asList("skill_1", "skill_2"), cv.getSkills());
        assertTrue(cv.getCompanyHistories().isEmpty());
    }
}