package br.com.rossalli.sample.java.api.hero.architecure.utils;

public enum PackageEnum {

    BASE_PACKAGE("br.com.rossalli.sample.java.api.hero"),
    CONTROLLER_PACKAGE(".controller"),
    DOMAIN_PACKAGE(".domain"),
    SERVICE_PACKAGE(".service"),
    SERVICE_IMPL_PACKAGE(".service.impl"),
    REQUEST_PACKAGE(".controller.request"),
    RESPONSE_PACKAGE(".controller.response"),
    ENTITY_PACKAGE(".domain.entity"),
    REPOSITORY_PACKAGE(".domain.repository"),
    DTO_PACKAGE(".service.dto");

    private final String packagePath;

    PackageEnum(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getPackagePath() {
        if(this == BASE_PACKAGE) {
            return packagePath;
        }
        return BASE_PACKAGE.packagePath.concat(packagePath);
    }
}
