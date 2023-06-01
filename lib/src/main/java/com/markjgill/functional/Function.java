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
}
