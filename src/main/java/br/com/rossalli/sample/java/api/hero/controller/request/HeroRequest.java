package br.com.rossalli.sample.java.api.hero.controller.request;

import br.com.rossalli.sample.java.api.hero.common.constant.Universe;
import br.com.rossalli.sample.java.api.hero.service.dto.HeroDTO;

import java.io.Serializable;

public class HeroRequest implements Serializable {

    private String name;
    private String codename;
    private Universe universe;

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

    public HeroDTO toDTO() {
        HeroDTO hero = new HeroDTO();
        hero.setName(this.name);
        hero.setCodename(this.codename);
        hero.setUniverse(this.universe);
        return hero;
    }
}
