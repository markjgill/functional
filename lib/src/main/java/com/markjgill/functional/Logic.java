package com.markjgill.functional;

import com.google.common.collect.Range;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.collection.Traversable;
import io.vavr.control.Option;
import org.apache.commons.lang3.BooleanUtils;

import java.util.function.Predicate;

public class Logic
{
  public static <A> Function2<A, A, A> defaultTo()
  {
    return (defaultVal, val) -> Option.of(val).getOrElse(defaultVal);
  }

  public static <A> Function1<A, A> defaultTo(A defaultVal)
  {
    return Logic.<A>defaultTo().apply(defaultVal);
  }

  public static Function1<Boolean, Boolean> not()
  {
    return BooleanUtils::negate;
  }

  public static <A> Function1<Function1<A, Boolean>, Function1<A, Boolean>> complement()
  {
    return func -> not().compose(func);
  }

  public static <A> Function1<A, Boolean> complement(Function1<A, Boolean> func)
  {
    return Logic.<A>complement().apply(func);
  }

  public static <A extends Comparable<A>> Function3<A, A, A, Boolean> between()
  {
    return (start, end, val) -> Range.closed(start, end).contains(val);
  }

  public static <A extends Comparable<A>> Function1<A, Boolean> between(A start, A end)
  {
    return Logic.<A>between().apply(start, end);
  }

  public static Function2<Boolean, Boolean, Boolean> and()
  {
    return (bool, otherBool) -> otherBool && bool;
  }

  public static Function1<Boolean ,Boolean> and(Boolean bool)
  {
    return Logic.and().apply(bool);
  }

  public static <A> Function2<Function1<A, Boolean>, Function1<A, Boolean>, Function1<A, Boolean>> both()
  {
    return (fn, otherFn) -> val -> and(fn.apply(val)).apply(otherFn.apply(val));
  }

  public static <A> Function1<A, Boolean> both(Function1<A, Boolean> fn, Function1<A, Boolean> otherFn)
  {
    return Logic.<A>both().apply(fn, otherFn);
  }

  public static Function2<Boolean, Boolean, Boolean> or()
  {
    return (bool, otherBool) -> bool || otherBool;
  }

  public static Boolean or(Boolean bool, Boolean otherBool)
  {
    return or().apply(bool, otherBool);
  }

  public static <A> Function2<Function1<A, Boolean>, Function1<A, Boolean>, Function1<A, Boolean>> either()
  {
    return (fn, otherFn) -> val -> or(fn.apply(val), otherFn.apply(val));
  }

  public static <A> Function1<A, Boolean> either(Function1<A, Boolean> fn, Function1<A, Boolean> otherFn)
  {
    return Logic.<A>either().apply(fn, otherFn);
  }

  public static <A> Function2<Traversable<Function1<A, Boolean>>, A, Boolean> allPass()
  {
    return (fns, val) -> fns.reduce(both()).apply(val);
  }

  public static <A> Function1<A, Boolean> allPass(Traversable<Function1<A, Boolean>> fns)
  {
    return Logic.<A>allPass().apply(fns);
  }

  public static <A> Function2<Traversable<Function1<A, Boolean>>, A, Boolean> anyPass()
  {
    return (fns, val) -> fns.reduce(either()).apply(val);
  }

  public static <A> Function1<A, Boolean> anyPass(Traversable<Function1<A, Boolean>> fns)
  {
    return Logic.<A>anyPass().apply(fns);
  }

  public static <A> Function3<Predicate<A>, Function1<A, A>, A, A> when()
  {
    return (condition, fn, val) -> condition.test(val) ? fn.apply(val) : val;
  }

  public static <A> Function1<A, A> when(Predicate<A> condition, Function1<A, A> fn)
  {
    return Logic.<A>when().apply(condition, fn);
  }
}
