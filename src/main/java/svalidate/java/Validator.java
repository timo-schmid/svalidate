package svalidate.java;

import java.util.List;

interface Validator<T> {

  public List<String> validate(T t);

}

