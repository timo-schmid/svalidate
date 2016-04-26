package svalidate.java;

import svalidate.java.Validator;
import java.util.function.Function;

public class Validation<T, V> {

    private final String label;

    private final Function<T, V> get;

    private final Validator<V> validator;

    public Validation(String label, Function<T, V> get, Validator<V> validator) {
        this.label     = label;
        this.get       = get;
        this.validator = validator;
    }

    public String label() {
        return this.label;
    }

    public Function<T, V> get() {
        return this.get;
    }

    public Validator<V> validator() {
        return this.validator;
    }

}

