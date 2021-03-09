package br.com.rossalli.sample.java.api.hero.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String resource, String identify) {
        super("%s with identify %s not found");
    }
}
