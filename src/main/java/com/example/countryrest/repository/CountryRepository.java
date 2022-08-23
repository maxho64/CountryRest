package com.example.countryrest.repository;


import com.example.countryrest.entity.Country;
import com.example.countryrest.entity.Currency;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();
    private static int ID = 1;

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setId(ID++);
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);

        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setId(ID++);
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setId(ID++);
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.put(uk.getName(), uk);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }

    public void save(Country country){
        country.setId(ID++);
        countries.put(country.getName(), country);
    }

    public void update(Country country){
        countries.put(country.getName(), country);
    }

    public Collection<Country> getAll(){
        return countries.values();
    }

    public Country getById(int id){
        return getAll().stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void delete(int id){
        Country country = getById(id);
        countries.remove(country.getName());
    }
}