package br.com.rossalli.sample.java.api.hero.architecure.tests.rules;

import br.com.rossalli.sample.java.api.hero.architecure.utils.ArchConditionCustom;
import br.com.rossalli.sample.java.api.hero.architecure.utils.PackageEnum;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;

public class ServiceRules {

    @ArchTest
    static ArchRule dtos_should_be_in_service_dto_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("DTO")
                    .should().resideInAPackage(PackageEnum.DTO_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule service_should_be_in_service_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameEndingWith("Service")
                    .should()
                    .resideInAnyPackage(PackageEnum.SERVICE_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule service_impl_should_be_in_service_impl_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameEndingWith("ServiceImpl")
                    .should()
                    .resideInAnyPackage(PackageEnum.SERVICE_IMPL_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule service_impl_should_be_annotated_with_service =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("ServiceImpl")
                    .should().beAnnotatedWith(Service.class);

    @ArchTest
    static ArchRule service_should_be_interfaces =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameEndingWith("Service")
                    .should().beInterfaces();

    @ArchTest
    static ArchRule dto_in_should_be_to_entity_method =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("DTOIn")
                    .should(ArchConditionCustom.containspecificmethod("toEntity"));
    @ArchTest
    static ArchRule dto_out_should_be_from_dto_method =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("DTOOut")
                    .should(ArchConditionCustom.containspecificmethod("fromEntity"));

    @ArchTest
    static ArchRule request_classes_should_be_named_x_request =
            ArchRuleDefinition.classes().that()
                    .resideInAPackage(PackageEnum.DTO_PACKAGE.getPackagePath())
                    .should().haveSimpleNameContaining("DTO");


}
