package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Try;

import java.util.function.Consumer;

public class Function
{
  public static <A, B, C> Function1<Function2<A, B, C>, Function2<B, A, C>> flip()
  {
    return Function2::reversed;
  }

  public static <A, B, C> Function2<B, A, C> flip(Function2<A, B, C> fn)
  {
    return Function.<A, B, C>flip().apply(fn);
  }

  public static <A, B> Function3<Function1<A, B>, B, Consumer<Throwable>, Function1<A, B>> tryOr()
  {
    return (fn, defaultVal, onFailure) -> val -> Try.of(() -> fn.apply(val))
        .onFailure(onFailure)
        .getOrElse(defaultVal);
  }

  public static <A, B> Function1<A, B> tryOr(Function1<A, B> fn, B defaultVal, Consumer<Throwable> onFailure)
  {
    return Function.<A, B>tryOr().apply(fn, defaultVal, onFailure);
  }

  public static <A, B> Function2<Function1<A, B>, Consumer<Throwable>, Function1<A, B>> tryOrThrow()
  {
    return (fn, onFailure) -> val -> Try.of(() -> fn.apply(val))
        .onFailure(onFailure)
        .get();
  }

  public static <A, B> Function1<A, B> tryOrThrow(Function1<A, B> fn, Consumer<Throwable> onFailure)
  {
    return Function.<A, B> tryOrThrow().apply(fn, onFailure);
  }
}
