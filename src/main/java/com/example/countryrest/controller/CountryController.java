package com.example.countryrest.controller;

import com.example.countryrest.entity.Country;
import com.example.countryrest.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }


    @Operation(method = "GET", description = "Get all countries.", tags = "countries")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Countries not found"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the countries.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        Collection<Country> countries = service.getAll();
        if (countries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArrayList<>(countries), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getByName(@Parameter() @PathVariable String name) {
        Country country = service.findCountry(name);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Country> findCountry(@PathVariable int id) {
        Optional<Country> countryOptional = service.getById(id);
        if (countryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(countryOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country save(@RequestBody Country country) {
        service.save(country);
        return country;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable int id,
                                         @RequestBody Country country) {

        Optional<Country> dbCountryOptional = service.getById(id);
        if (dbCountryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Country dbCountry = dbCountryOptional.get();
        dbCountry.setName(country.getName());
        dbCountry.setCapital(country.getCapital());
        dbCountry.setCurrency(country.getCurrency());
        dbCountry.setPopulation(country.getPopulation());
        service.update(dbCountry);
        return new ResponseEntity<>(dbCountry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Country> dbCountryOptional = service.getById(id);
        if (dbCountryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(dbCountryOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
