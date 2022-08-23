package com.example.countryrest.controller;

import com.example.countryrest.entity.Country;
import com.example.countryrest.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Country> getAll() {
        return new ArrayList<>(service.getAll());
    }

    @GetMapping("/name/{name}")
    public Country getByName(@PathVariable String name){
        return service.findCountry(name);
    }


    @GetMapping("/{id}")
    public Country findCountry(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Country save(@RequestBody Country country) {
        service.save(country);
        return country;
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable int id, @RequestBody Country country) {
        Country dbCountry = service.getById(id);
        dbCountry.setName(country.getName());
        dbCountry.setCapital(country.getCapital());
        dbCountry.setCurrency(country.getCurrency());
        dbCountry.setPopulation(country.getPopulation());
        service.update(dbCountry);
        return dbCountry;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "success";
    }
}
