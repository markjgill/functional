package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.collection.List;

import static com.markjgill.functional.Collection.map;
import static com.markjgill.functional.Collection.reverse;
import static com.markjgill.functional.Logic.between;
import static com.markjgill.functional.Logic.defaultTo;

public class Main {

  public static void main(String[] args) {

    var ans = between("m", "z")
            .compose(defaultTo("a"))
            .apply("p");
  }
}
