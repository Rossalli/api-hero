package br.com.rossalli.sample.java.api.hero.controller;

import br.com.rossalli.sample.java.api.hero.controller.request.HeroRequest;
import br.com.rossalli.sample.java.api.hero.controller.response.HeroResponse;
import br.com.rossalli.sample.java.api.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeroResponse create(@RequestBody HeroRequest request) {
        return HeroResponse.fromDTO(heroService.save(request.toDTO()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HeroResponse findById(@PathVariable("id") Long id) {
        return HeroResponse.fromDTO(
                heroService.getById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HeroResponse> findAll(@RequestParam(value = "universe", required = false) String universe) {
        return heroService.getAll().stream()
                .map(HeroResponse::fromDTO).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HeroResponse update(@PathVariable("id") Long id, @RequestBody HeroRequest request) {
        return HeroResponse.fromDTO(
                heroService.update(id, request.toDTO()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        heroService.delete(id);
    }
}
