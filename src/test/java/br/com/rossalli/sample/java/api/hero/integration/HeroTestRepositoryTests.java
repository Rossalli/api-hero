package br.com.rossalli.sample.java.api.hero.integration;

import br.com.rossalli.sample.java.api.hero.common.constant.Universe;
import br.com.rossalli.sample.java.api.hero.domain.entity.Hero;
import br.com.rossalli.sample.java.api.hero.domain.repository.HeroRepository;
import br.com.rossalli.sample.java.api.hero.exceptions.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeroTestRepositoryTests {

    private static final Long HERO_ID = 1L;

    @Autowired
    private HeroRepository heroRepository;

    @Container
    public static PostgreSQLContainer container =
            new PostgreSQLContainer("postgres:9.6-alpine")
            .withDatabaseName("herodb")
            .withPassword("herodb")
            .withUsername("herodb");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

    @Test
    @Order(1)
    @DisplayName("Create a new hero with success")
    public void  testCreateSuccess() {
        Hero savedHero = heroRepository.save(buildDummyHero());
        Long heroId = savedHero.getId();
        assertNotNull(heroId);
        assertEquals(HERO_ID, savedHero.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Error on hero create with invalid name")
    public void  testCreateWithError() {
        Hero hero = new Hero();
        hero.setName("");
        hero.setCodename("The Flash");
        hero.setUniverse(Universe.DC_COMICS);
        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> heroRepository.save(hero),
                "Expected doThing() to throw, but it didn't"
        );

        assertTrue(exception.getMessage().contains("Validation failed"));

    }

    @Test
    @Order(3)
    @DisplayName("Get a hero by id")
    public void  testGetByIdSuccess() {
        Optional<Hero> hero = heroRepository.findById(HERO_ID);
        assertTrue(hero.isPresent());
    }

    @Test
    @Order(4)
    @DisplayName("Not found a hero by id")
    public void  testGetByIdNotFound() {
        Optional<Hero> hero = heroRepository.findById(42L);
        assertTrue(hero.isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("Get all heroes")
    public void  testGetAllHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        assertFalse(heroes.isEmpty());
        assertEquals(1, heroes.size());
    }

    @Test
    @Order(6)
    @DisplayName("Update Hero")
    public void  updateHero() {
        Hero hero = heroRepository.findById(HERO_ID)
                .orElseThrow(() -> new NotFoundException("Hero", Long.toString(HERO_ID) ));
        hero.setName("Wally West");
        Hero updatedHero = heroRepository.save(hero);
        assertEquals(hero.getId(), updatedHero.getId());
        assertEquals(hero.getName(), updatedHero.getName());
        assertEquals("Wally West", updatedHero.getName());
    }

    @Test
    @Order(7)
    @DisplayName("Delete hero")
    public void  deleteHero() {
        Optional<Hero> heroBeforeDelete = heroRepository.findById(HERO_ID);
        assertTrue(heroBeforeDelete.isPresent());
        heroRepository.deleteById(HERO_ID);
        Optional<Hero> heroAfterDelete = heroRepository.findById(HERO_ID);
        assertFalse(heroAfterDelete.isPresent());
    }

    @Test
    @Order(8)
    @DisplayName("No heroes founded")
    public void  testGetAllNotFound() {
        List<Hero> heroes = heroRepository.findAll();
        assertEquals(0, heroes.size());
    }


    private Hero buildDummyHero() {
        Hero hero = new Hero();
        hero.setName("Barry Allen");
        hero.setCodename("The Flash");
        hero.setUniverse(Universe.DC_COMICS);
        return hero;
    }
}
