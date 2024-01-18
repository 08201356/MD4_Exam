package base.controller;

import base.model.City;
import base.model.Country;
import base.service.CityService;
import base.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @ModelAttribute("countries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }
    @GetMapping("")
    public String showList(Model model){
        Iterable<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "/list";
    }
    @GetMapping("/create")
    private ModelAndView showCreateForm(){
        ModelAndView model = new ModelAndView("/create");
        model.addObject("city", new City());
        return model;
    }
    @PostMapping("/create")
    private ModelAndView saveCity(@ModelAttribute("city")City city){
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        modelAndView.addObject("city", city);
        cityService.save(city);
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editCityForm(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView updateCity(@ModelAttribute("city") City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        cityService.remove(id);
        return "redirect:/cities";
    }
}
