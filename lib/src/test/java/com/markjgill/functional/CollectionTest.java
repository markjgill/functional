package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.collection.Array;
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

  @Test
  void join()
  {
    assertThat(Collection.join("; ").apply(List.of(1, 2, 3))).isEqualTo("1; 2; 3");
    assertThat(Collection.join(":").apply(Array.of("a", "b", "c"))).isEqualTo("a:b:c");
  }

  @Test
  void head()
  {
    assertThat(Collection.head().apply(List.of(1, 2, 3))).isEqualTo(1);
    assertThat(Collection.head().apply(Array.of("a", "b", "c"))).isEqualTo("a");
  }

  @Test
  void last()
  {
    assertThat(Collection.last().apply(List.of(1, 2, 3))).isEqualTo(3);
    assertThat(Collection.last().apply(Array.of("a", "b", "c"))).isEqualTo("c");
  }

  @Test
  void tail()
  {
    assertThat(Collection.tail().apply(List.of(1, 2, 3))).isEqualTo(List.of(2, 3));
    assertThat(Collection.tail().apply(Array.of("a", "b", "c"))).isEqualTo(List.of("b", "c"));
  }

  @Test
  void distinct()
  {
    assertThat(Collection.distinct().apply(List.of(1, 1, 2, 3, 2))).isEqualTo(List.of(1, 2, 3));
    assertThat(Collection.distinct().apply(Array.of("a", "b", "b"))).isEqualTo(Array.of("a", "b"));
  }

  @Test
  void append()
  {
    assertThat(Collection.append(3).apply(List.of(1, 2))).isEqualTo(List.of(1, 2, 3));
    assertThat(Collection.append("a").apply(List.of())).isEqualTo(List.of("a"));
  }

  @Test
  void prepend()
  {
    assertThat(Collection.prepend(3).apply(List.of(1, 2))).isEqualTo(List.of(3, 1, 2));
    assertThat(Collection.prepend("a").apply(List.of())).isEqualTo(List.of("a"));
  }

  @Test
  void union()
  {
    assertThat(Collection.union(List.of(1, 2)).apply(List.of(2, 3))).isEqualTo(List.of(1, 2, 2, 3));
    assertThat(Collection.union(Array.of(1, 2)).apply(Array.of(2, 3))).isEqualTo(Array.of(1, 2, 2, 3));
    assertThat(Collection.union(Array.of(3)).apply(Array.of(1, 2))).isEqualTo(Array.of(3, 1, 2));
  }

  @Test
  void intersection()
  {
    assertThat(Collection.intersection(List.of(1, 2, 3)).apply(List.of(3, 4))).isEqualTo(List.of(3));
    assertThat(Collection.intersection(List.of(1, 2)).apply(List.of(2, 3, 4))).isEqualTo(List.of(2));
    assertThat(Collection.intersection(List.of(1, 2)).apply(List.of(3, 4))).isEmpty();
  }

  @Test
  void reverse()
  {
    assertThat(Collection.reverse().apply(List.of(1, 3, 2, 4))).isEqualTo(List.of(4, 2, 3, 1));
    assertThat(Collection.reverse().apply(Array.of("b", "a", "c"))).isEqualTo(Array.of("c", "a", "b"));
  }

  @Test
  void take()
  {
    assertThat(Collection.take(2).apply(List.of(1, 2, 3, 4))).isEqualTo(List.of(1, 2));
    assertThat(Collection.take(1).apply(Array.of("a", "b"))).isEqualTo(List.of("a"));
    assertThat(Collection.take(0).apply(Array.of("a", "b"))).isEmpty();
  }

  @Test
  void drop()
  {
    assertThat(Collection.drop(2).apply(List.of(1, 2, 3, 4))).isEqualTo(List.of(3, 4));
    assertThat(Collection.drop(1).apply(Array.of("a", "b"))).isEqualTo(List.of("b"));
    assertThat(Collection.drop(2).apply(Array.of("a", "b"))).isEmpty();
  }

  @Test
  void nth()
  {
    assertThat(Collection.nth(2).apply(List.of(1, 2, 3))).isEqualTo(3);
    assertThat(Collection.nth(1).apply(Array.of("a", "b", "c"))).isEqualTo("b");
    assertThat(Collection.nth(0).apply(Array.of("a", "b"))).isEqualTo("a");
  }
}
