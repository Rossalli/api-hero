package br.com.rossalli.sample.java.api.hero.architecure.tests.rules;

import br.com.rossalli.sample.java.api.hero.architecure.utils.ArchConditionCustom;
import br.com.rossalli.sample.java.api.hero.architecure.utils.PackageEnum;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

public class ApplicationRules {

    @ArchTest
    static ArchRule controllers_should_be_in_application_controller_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Controller")
                    .should().resideInAPackage(PackageEnum.CONTROLLER_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule request_should_be_in_application_request_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Request")
                    .should().resideInAPackage(PackageEnum.REQUEST_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule response_should_be_in_application_response_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Response")
                    .should().resideInAPackage(PackageEnum.RESPONSE_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule controller_should_be_annotated_with_rest_controller =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Controller")
                    .should().beAnnotatedWith(RestController.class);

    @ArchTest
    static ArchRule request_and_response_should_be_implementes_serializable =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Request").or().haveSimpleNameContaining("Response")
                    .should().implement(Serializable.class);

    @ArchTest
    static ArchRule controller_should_be_only_public_methods =
            ArchRuleDefinition.methods().that()
                    .areDeclaredInClassesThat().haveSimpleNameContaining("Controller")
                    .should().bePublic();

    @ArchTest
    static ArchRule request_should_be_to_dto_method =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Request")
                    .should(ArchConditionCustom.containspecificmethod("toDTO"));

    @ArchTest
    static ArchRule response_should_be_from_dto_method =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Response")
                    .should(ArchConditionCustom.containspecificmethod("fromDTO"));

    @ArchTest
    static ArchRule controller_should_be_inject_only_service_impl_class =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameContaining("Controller")
                    .should(ArchConditionCustom.injectOnlyType("Service"));

    @ArchTest
    static ArchRule controller_not_should_access_domain =
            ArchRuleDefinition.noClasses().that().resideInAPackage(PackageEnum.CONTROLLER_PACKAGE.getPackagePath())
                    .should().accessClassesThat()
                    .resideInAPackage(PackageEnum.DOMAIN_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule controllers_classes_should_be_named_x_controller =
            ArchRuleDefinition.classes().that()
                    .areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule request_classes_should_be_named_x_request =
            ArchRuleDefinition.classes().that()
                    .resideInAPackage(PackageEnum.REQUEST_PACKAGE.getPackagePath())
                    .should().haveSimpleNameEndingWith("Request");

    @ArchTest
    static ArchRule response_classes_should_be_named_x_response =
            ArchRuleDefinition.classes().that()
                    .resideInAPackage(PackageEnum.RESPONSE_PACKAGE.getPackagePath())
                    .should().haveSimpleNameEndingWith("Response");


}
