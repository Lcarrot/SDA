package ru.tyshchenko.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.inject.Singleton;

@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
