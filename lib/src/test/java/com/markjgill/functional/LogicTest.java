package com.markjgill.functional;

import io.vavr.Function1;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogicTest
{
  @Test
  void defaultTo()
  {
    String result = Logic.defaultTo("HelloWorld").apply("Hello");
    assertThat(result).isEqualTo("Hello");

    Integer defaultResult = Logic.defaultTo(42).apply(null);
    assertThat(defaultResult).isEqualTo(42);
  }

  @Test
  void not()
  {
    assertThat(Logic.not().apply(true)).isFalse();
    assertThat(Logic.not().apply(false)).isTrue();
    assertThat(Logic.not().apply(null)).isNull();
  }

  @Test
  void complement()
  {
    assertThat(Logic.complement(Function1.identity()).apply(true)).isFalse();
    assertThat(Logic.complement(Function1.identity()).apply(false)).isTrue();
    assertThat(Logic.complement(Function1.identity()).apply(null)).isNull();
  }

  @Test
  void between()
  {
    assertThat(Logic.between(1, 5).apply(3)).isTrue();
    assertThat(Logic.between(1, 5).apply(6)).isFalse();
    assertThat(Logic.between("aaa", "zzz").apply("doh")).isTrue();
    assertThat(Logic.between("d", "h").apply("o")).isFalse();
  }

  @Test
  void or()
  {
    assertThat(Logic.or(true, true)).isTrue();
    assertThat(Logic.or(true, false)).isTrue();
    assertThat(Logic.or(false, true)).isTrue();
    assertThat(Logic.or(false, false)).isFalse();
  }

  @Test
  void either()
  {
    assertThat(Logic.either(Function1.constant(true), Function1.identity()).apply(true)).isTrue();
    assertThat(Logic.either(Function1.constant(true), Function1.identity()).apply(false)).isTrue();
    assertThat(Logic.either(Function1.constant(false), Function1.identity()).apply(true)).isTrue();
    assertThat(Logic.either(Function1.constant(false), Function1.identity()).apply(false)).isFalse();
  }
}
