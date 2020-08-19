package com.bjss.controller;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bjss.domain.CompanyHistory;
import com.bjss.domain.Cv;
import com.bjss.repository.CvRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest
@RunWith(SpringRunner.class)
public class CvApiTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CvRepository cvRepository;

    @Test
    public void whenGetRequestToCvsApiThenReturnAllItems() throws Exception {
        when(cvRepository.findAll()).thenReturn(asList( //
                cv(1L, "Jim", asList("Java", "Mockito"),
                        asList(companyHistory("Amazon", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                                companyHistory("BJSS", LocalDate.of(2016, 1, 1), null))),
                cv(2L, "Jack", asList("Angular", "JavaScript"),
                        asList(companyHistory("Google", LocalDate.of(2012, 1, 1), LocalDate.of(2018, 12, 30)),
                                companyHistory("BJSS", LocalDate.of(2019, 1, 1), null)))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cvs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].cvId", is(1)))
                .andExpect(jsonPath("$.[0].name", is("Jim")))
                .andExpect(jsonPath("$.[0].skills.[0]", is("Java")))
                .andExpect(jsonPath("$.[0].skills.[1]", is("Mockito")))
                .andExpect(jsonPath("$.[0].companyHistories.[0].companyName", is("Amazon")))
                .andExpect(jsonPath("$.[0].companyHistories.[0].startDate", is("2010-01-01")))
                .andExpect(jsonPath("$.[0].companyHistories.[0].endDate", is("2015-12-30")))
                .andExpect(jsonPath("$.[0].companyHistories.[1].companyName", is("BJSS")))
                .andExpect(jsonPath("$.[0].companyHistories.[1].startDate", is("2016-01-01")))
                .andExpect(jsonPath("$.[0].companyHistories.[1].endDate", nullValue()))
                .andExpect(jsonPath("$.[1].cvId", is(2)))
                .andExpect(jsonPath("$.[1].name", is("Jack")))
                .andExpect(jsonPath("$.[1].skills.[0]", is("Angular")))
                .andExpect(jsonPath("$.[1].skills.[1]", is("JavaScript")))
                .andExpect(jsonPath("$.[1].companyHistories.[0].companyName", is("Google")))
                .andExpect(jsonPath("$.[1].companyHistories.[0].startDate", is("2012-01-01")))
                .andExpect(jsonPath("$.[1].companyHistories.[0].endDate", is("2018-12-30")))
                .andExpect(jsonPath("$.[1].companyHistories.[1].companyName", is("BJSS")))
                .andExpect(jsonPath("$.[1].companyHistories.[1].startDate", is("2019-01-01")))
                .andExpect(jsonPath("$.[1].companyHistories.[1].endDate", nullValue()));
    }

    @Test
    public void whenGetRequestWithIdToCvsApiThenReturnCorrectElement() throws Exception {
        when(cvRepository.findById(1L)).thenReturn(Optional.of(cv(1L, "Jim", asList("Java", "Mockito"),
                asList(companyHistory("Amazon", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                        companyHistory("BJSS", LocalDate.of(2016, 1, 1), null)))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cvs/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cvId", is(1)))
                .andExpect(jsonPath("$.name", is("Jim")))
                .andExpect(jsonPath("$.skills.[0]", is("Java")))
                .andExpect(jsonPath("$.skills.[1]", is("Mockito")))
                .andExpect(jsonPath("$.companyHistories.[0].companyName", is("Amazon")))
                .andExpect(jsonPath("$.companyHistories.[0].startDate", is("2010-01-01")))
                .andExpect(jsonPath("$.companyHistories.[0].endDate", is("2015-12-30")))
                .andExpect(jsonPath("$.companyHistories.[1].companyName", is("BJSS")))
                .andExpect(jsonPath("$.companyHistories.[1].startDate", is("2016-01-01")))
                .andExpect(jsonPath("$.companyHistories.[1].endDate", nullValue()));
    }

    @Test
    public void whenGetRequestWithInvalidIdToCvsApiThenReturnNotFound() throws Exception {
        when(cvRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cvs/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Could not find CV 1"));
    }

    @Test
    public void whenPostRequestWithJsonToCvsApiThenReturnInsertedValue() throws Exception {
        Cv cv = cv(1L, "Jim", asList("Java", "Mockito"),
                asList(companyHistory("Amazon", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                        companyHistory("BJSS", LocalDate.of(2016, 1, 1), null)));
        String json = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(cv);
        when(cvRepository.save(cv)).thenReturn(cv);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cvs")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(
                jsonPath("$.cvId", is(1))).andExpect(jsonPath("$.name", is("Jim"))).andExpect(
                jsonPath("$.skills.[0]", is("Java"))).andExpect(jsonPath("$.skills.[1]", is("Mockito"))).andExpect(
                jsonPath("$.companyHistories.[0].companyName", is("Amazon"))).andExpect(
                jsonPath("$.companyHistories.[0].startDate", is("2010-01-01"))).andExpect(
                jsonPath("$.companyHistories.[0].endDate", is("2015-12-30"))).andExpect(
                jsonPath("$.companyHistories.[1].companyName", is("BJSS"))).andExpect(
                jsonPath("$.companyHistories.[1].startDate", is("2016-01-01"))).andExpect(
                jsonPath("$.companyHistories.[1].endDate", nullValue()));
    }

    @Test
    public void whenPostRequestWithInvalidJsonToCvsApiThenReturnBadRequest() throws Exception {
        Cv cv = cv(1L, "", emptyList(), asList(companyHistory("", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                companyHistory("BJSS", null, null)));
        String json = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(cv);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cvs")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(
                jsonPath("$.name", is("Name is mandatory"))).andExpect(
                jsonPath("$.skills", is("Skill have to contains element(s)")));
    }

    @Test
    public void whenPutRequestWithJsonToCvsApiThenReturnUpdatedValue() throws Exception {
        Cv oldCv = cv(1L, "Jim", asList("JavaScript", "Mockito"),
                asList(companyHistory("Google", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                        companyHistory("BJSS", LocalDate.of(2016, 1, 1), null)));
        Cv newCv = cv(1L, "Jim", asList("Java", "Mockito"),
                asList(companyHistory("Amazon", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                        companyHistory("BJSS", LocalDate.of(2016, 1, 1), null)));
        String json = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(newCv);
        when(cvRepository.findById(1L)).thenReturn(Optional.of(oldCv));
        when(cvRepository.save(newCv)).thenReturn(newCv);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cvs/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(
                jsonPath("$.cvId", is(1))).andExpect(jsonPath("$.name", is("Jim"))).andExpect(
                jsonPath("$.skills.[0]", is("Java"))).andExpect(jsonPath("$.skills.[1]", is("Mockito"))).andExpect(
                jsonPath("$.companyHistories.[0].companyName", is("Amazon"))).andExpect(
                jsonPath("$.companyHistories.[0].startDate", is("2010-01-01"))).andExpect(
                jsonPath("$.companyHistories.[0].endDate", is("2015-12-30"))).andExpect(
                jsonPath("$.companyHistories.[1].companyName", is("BJSS"))).andExpect(
                jsonPath("$.companyHistories.[1].startDate", is("2016-01-01"))).andExpect(
                jsonPath("$.companyHistories.[1].endDate", nullValue()));
    }

    @Test
    public void whenPutRequestWithInvalidJsonToCvsApiThenReturnBadRequest() throws Exception {
        Cv newCv = cv(1L, "", emptyList(),
                asList(companyHistory("Amazon", LocalDate.of(2010, 1, 1), LocalDate.of(2015, 12, 30)),
                        companyHistory("BJSS", LocalDate.of(2016, 1, 1), null)));
        String json = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(newCv);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cvs/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(
                jsonPath("$.name", is("Name is mandatory"))).andExpect(
                jsonPath("$.skills", is("Skill have to contains element(s)")));
    }

    @Test
    public void whenDeleteRequestWithIdToCvsApiThenReturnOkResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cvs/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private Cv cv(long cvId, String name, List<String> skills, List<CompanyHistory> companyHistories) {
        return Cv.builder().cvId(cvId).name(name).skills(skills).companyHistories(companyHistories).build();
    }

    private CompanyHistory companyHistory(String name, LocalDate startDate, LocalDate endDate) {
        return CompanyHistory.builder().companyName(name).startDate(startDate).endDate(endDate).build();
    }
}