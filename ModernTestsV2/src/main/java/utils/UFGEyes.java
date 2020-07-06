package utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UFGEyes {
    String testName() default "Default Test Name.";
    boolean checkBefore() default false;
    boolean checkAfter() default false;
}
