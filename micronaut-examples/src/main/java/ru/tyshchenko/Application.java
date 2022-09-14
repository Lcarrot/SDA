package ru.tyshchenko;

import javax.validation.constraints.NotNull;

import io.micronaut.runtime.Micronaut;

@NotNull
public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
