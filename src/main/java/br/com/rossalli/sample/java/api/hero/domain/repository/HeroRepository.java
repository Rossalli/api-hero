package br.com.rossalli.sample.java.api.hero.domain.repository;

import br.com.rossalli.sample.java.api.hero.domain.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
}
