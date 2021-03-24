package br.com.rossalli.sample.java.api.hero.service.impl;

import br.com.rossalli.sample.java.api.hero.domain.entity.Hero;
import br.com.rossalli.sample.java.api.hero.domain.repository.HeroRepository;
import br.com.rossalli.sample.java.api.hero.exceptions.NotFoundException;
import br.com.rossalli.sample.java.api.hero.service.HeroService;
import br.com.rossalli.sample.java.api.hero.service.dto.HeroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImplementacaoAAA implements HeroService {

    private final HeroRepository heroRepository;
    private static final Logger log = LoggerFactory.getLogger(HeroServiceImplementacaoAAA.class);

    @Autowired
    public HeroServiceImplementacaoAAA(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public HeroDTO save(HeroDTO heroDTO) {
        return HeroDTO.fromEntity(
                heroRepository.save(heroDTO.toEntity()));
    }

    @Override
    public HeroDTO getById(Long id) {
        return HeroDTO.fromEntity(findById(id));
    }

    @Override
    public List<HeroDTO> getAll() {
        return heroRepository.findAll()
                .stream().map(HeroDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public HeroDTO update(Long id, HeroDTO heroDTO) {
        Hero hero = findById(id);
        hero.update(heroDTO.getName(), hero.getCodename(), hero.getUniverse());
        return HeroDTO.fromEntity(heroRepository.save(hero));
    }

    @Override
    public void delete(Long id) {
        Hero hero = findById(id);
        hero.inactive();
        heroRepository.save(hero);
    }

    private Hero findById(Long id) {
        return heroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hero", id.toString()));
    }
}
