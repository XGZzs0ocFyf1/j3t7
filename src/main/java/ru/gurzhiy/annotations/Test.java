package ru.gurzhiy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  This annotation tells {@link ru.gurzhiy.handlers.TestRunner} that public void method, annotated by it
 *  * should be used as test case.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

    int value() default 1;


}
