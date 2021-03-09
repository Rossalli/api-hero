package br.com.rossalli.sample.java.api.hero.architecure.tests.rules;

import br.com.rossalli.sample.java.api.hero.architecure.utils.PackageEnum;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

public class DomainRules {

    @ArchTest
    static ArchRule entities_should_be_in_domain_entity_package =
            ArchRuleDefinition.classes().that().areAnnotatedWith(Entity.class)
                    .should().resideInAPackage(PackageEnum.ENTITY_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule entities_should_be_in_anotted_with_entity =
            ArchRuleDefinition.classes().that().resideInAPackage(PackageEnum.ENTITY_PACKAGE.getPackagePath())
                    .should().beAnnotatedWith(Entity.class);

    @ArchTest
    static ArchRule repository_should_be_in_contact_repository_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Repository")
                    .should().resideInAPackage(PackageEnum.REPOSITORY_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule repositories_should_be_in_anotted_with_repository =
            ArchRuleDefinition.classes().that().resideInAPackage(PackageEnum.REPOSITORY_PACKAGE.getPackagePath())
                    .should().beAnnotatedWith(Repository.class);

    @ArchTest
    static ArchRule repository_should_be_interfaces_and_extends_jpa_repository =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Repository")
                    .should().beInterfaces().andShould().beAssignableTo(JpaRepository.class);
}
