package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionTest
{
  @Test
  void flatten()
  {
    assertThat(Collection.flatten().apply(List.of(List.of(1, 2), List.of(3), List.of(4, 5))))
        .isEqualTo(List.of(1, 2, 3, 4, 5));
  }

  @Test
  void map()
  {
    Function1<Integer, Integer> mapper = x -> x * 2;
    List<Integer> integers = List.of(1, 2, 3);

    assertThat(Collection.map(mapper).apply(integers)).isEqualTo(List.of(2, 4, 6));
  }

  @Test
  void flatMap()
  {
    Function1<Integer, Integer> mapper = x -> x * 2;

    assertThat(Collection.flatMap(mapper).apply(List.of(List.of(1, 2), List.of(3), List.of(4, 5))))
        .isEqualTo(List.of(2, 4, 6, 8, 10));
  }

  @Test
  void filter()
  {
    Predicate<Integer> predicate = x -> x % 2 == 0;
    List<Integer> integers = List.of(1, 2, 3);

    assertThat(Collection.filter(predicate).apply(integers)).isEqualTo(List.of(2));
  }

  @Test
  void reject()
  {
    Predicate<Integer> predicate = x -> x % 2 == 0;
    List<Integer> integers = List.of(1, 2, 3);

    assertThat(Collection.reject(predicate).apply(integers)).isEqualTo(List.of(1, 3));
  }

  @Test
  void any()
  {
    Predicate<Integer> predicate = x -> x % 2 == 0;

    assertThat(Collection.any(predicate).apply(List.of(1, 2, 3))).isTrue();
    assertThat(Collection.any(predicate).apply(List.of(1, 3, 5))).isFalse();
  }

  @Test
  void all()
  {
    Predicate<Integer> predicate = x -> x % 2 == 0;

    assertThat(Collection.all(predicate).apply(List.of(2, 4, 6))).isTrue();
    assertThat(Collection.all(predicate).apply(List.of(2, 4, 7))).isFalse();
  }

  @Test
  void none()
  {
    Predicate<Integer> predicate = x -> x % 2 == 0;

    assertThat(Collection.none(predicate).apply(List.of(1, 3, 5))).isTrue();
    assertThat(Collection.none(predicate).apply(List.of(1, 3, 6))).isFalse();
  }
}
