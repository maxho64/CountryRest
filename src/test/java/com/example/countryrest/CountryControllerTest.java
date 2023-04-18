package com.example.countryrest;

import com.example.countryrest.controller.CountryController;
import com.example.countryrest.entity.Country;
import com.example.countryrest.entity.Currency;
import com.example.countryrest.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        Country country = new Country();
        country.setId(1);
        country.setPopulation(46704314);
        country.setCapital("Madrid");
        country.setName("Spain");
        country.setCurrency(Currency.EUR);

        when(service.getAll()).thenReturn(List.of(country));

        this.mockMvc.perform(get("/country")).andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "{\n" +
                        "\"id\": 1,\n" +
                        "\"name\": \"Spain\",\n" +
                        "\"currency\": \"EUR\",\n" +
                        "\"capital\": \"Madrid\",\n" +
                        "\"population\": 46704314\n" +
                        "}\n" +
                        "]"));
    }
}
