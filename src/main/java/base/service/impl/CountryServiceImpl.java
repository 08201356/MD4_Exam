package base.service.impl;

import base.model.Country;
import base.repository.CountryRepository;
import base.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }
    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }
    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }
    @Override
    public void remove(Long id) {
        countryRepository.deleteById(id);
    }
}
