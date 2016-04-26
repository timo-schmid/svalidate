package svalidate.java;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.Arrays;

public class Form<T> implements Function<T,Map<String,List<String>>> {

    private Validation<T, ?>[] validators;

    public Form(Class<T> clazz, Validation<T, ?> ... validators) {
        this.validators = validators;
    }

    public Map<String,List<String>> validate(T t) {
        return this.apply(t);
    }

    public Map<String,List<String>> apply(T t) {
        return JavaInterop.<T>javaForm(Arrays.asList(this.validators)).apply(t);
    }

}

