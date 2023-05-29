package com.markjgill.functional;

import io.vavr.Function2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionTest
{
  @Test
  void flip()
  {
    Function2<String, Integer, String> fn = (string, integer) -> string + " " + integer;
    assertThat(Function.flip(fn).apply(5, "Hello World")).isEqualTo("Hello World 5");
  }
}
