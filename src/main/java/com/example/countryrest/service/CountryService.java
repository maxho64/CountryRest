package com.example.countryrest.service;

import com.example.countryrest.entity.Country;
import com.example.countryrest.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Country findCountry(String name) {
        return repository.findCountry(name);
    }

    public void save(Country country){
        repository.save(country);
    }

    public void update(Country country){
        repository.update(country);
    }

    public Collection<Country> getAll(){
        return repository.getAll();
    }

    public Optional<Country> getById(int id){
        return repository.getById(id);
    }

    public void delete(Country country){
        repository.delete(country);
    }
}
