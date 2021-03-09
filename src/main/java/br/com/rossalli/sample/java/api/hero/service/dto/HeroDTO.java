package br.com.rossalli.sample.java.api.hero.service.dto;

import br.com.rossalli.sample.java.api.hero.common.constant.Universe;
import br.com.rossalli.sample.java.api.hero.domain.entity.Hero;

public class HeroDTO {
    private Long id;
    private String name;
    private String codename;
    private Universe universe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public Hero toEntity() {
        Hero hero = new Hero();
        hero.setId(this.id);
        hero.setName(this.name);
        hero.setCodename(this.codename);
        hero.setUniverse(this.universe);
        return hero;
    }

    public static HeroDTO fromEntity(Hero hero) {
        HeroDTO dto = new HeroDTO();
        dto.setId(hero.getId());
        dto.setName(hero.getName());
        dto.setCodename(hero.getCodename());
        dto.setUniverse(hero.getUniverse());
        return dto;
    }
}
