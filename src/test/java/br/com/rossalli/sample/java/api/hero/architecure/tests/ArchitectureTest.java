package br.com.rossalli.sample.java.api.hero.architecure.tests;

import br.com.rossalli.sample.java.api.hero.architecure.tests.rules.ApplicationRules;
import br.com.rossalli.sample.java.api.hero.architecure.tests.rules.DomainRules;
import br.com.rossalli.sample.java.api.hero.architecure.tests.rules.ServiceRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import com.tngtech.archunit.library.dependencies.SliceRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

@AnalyzeClasses(packages = "br.com.rossalli.sample.java.api.hero", importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

    @ArchTest
    static ArchRule dependency_injection_only_by_constructor =
            GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    SliceRule dependency_cycle = SlicesRuleDefinition.slices()
            .matching("..(*)")
            .should()
            .beFreeOfCycles();

    @ArchTest
    static ArchRules applicationRules = ArchRules.in(ApplicationRules.class);

    @ArchTest
    static ArchRules serviceRules = ArchRules.in(ServiceRules.class);

    @ArchTest
    static ArchRules domainRules = ArchRules.in(DomainRules.class);

}
