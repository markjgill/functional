package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.Traversable;

import java.util.function.Predicate;

public class Collection
{
  public static <A> Function1<Traversable<Traversable<A>>, Traversable<A>> flatten()
  {
    return col -> col.flatMap(Function1.identity());
  }

  public static <A, B> Function2<Function1<A, B>, Traversable<A>, Traversable<B>> map()
  {
    return (fn, col) -> col.map(fn);
  }

  public static <A, B> Function1<Traversable<A>, Traversable<B>> map(Function1<A, B> fn)
  {
    return Collection.<A, B>map().apply(fn);
  }

  public static <A, B> Function2<Function1<A, B>, Traversable<Traversable<A>>, Traversable<B>> flatMap()
  {
    return (fn, col) -> map(fn).compose(flatten()).apply(col);
  }

  public static <A, B> Function1<Traversable<Traversable<A>>, Traversable<B>> flatMap(Function1<A, B> fn)
  {
    return Collection.<A, B>flatMap().apply(fn);
  }

  public static <A> Function2<Predicate<A>, Traversable<A>, Traversable<A>> filter()
  {
    return (fn, col) -> col.filter(fn);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> filter(Predicate<A> predicate)
  {
    return Collection.<A>filter().apply(predicate);
  }

  public static <A> Function2<Predicate<A>, Traversable<A>, Traversable<A>> reject()
  {
    return (fn, col) -> col.reject(fn);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> reject(Predicate<A> predicate)
  {
    return Collection.<A>reject().apply(predicate);
  }
}
