package com.markjgill.functional;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;

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

  public static <A> Function1<A, Function0<A>> constant()
  {
    return Function0::constant;
  }

  public static <A> Function0<A> constant(A val)
  {
    return Function.<A>constant().apply(val);
  }

  public static Function0<Boolean> T()
  {
    return constant(true);
  }

  public static Function0<Boolean> F()
  {
    return constant(false);
  }
}
