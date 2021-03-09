package br.com.rossalli.sample.java.api.hero.service;

import br.com.rossalli.sample.java.api.hero.service.dto.HeroDTO;

import java.util.List;

public interface HeroService extends CrudService<HeroDTO> {

    HeroDTO save(HeroDTO dto);

    HeroDTO getById(Long id);

    List<HeroDTO> getAll();

    HeroDTO update(Long id, HeroDTO input);

    void delete(Long id);

}
