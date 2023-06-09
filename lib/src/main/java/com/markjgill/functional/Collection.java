package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;

import java.util.function.Predicate;

import static com.markjgill.functional.Logic.not;

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

  public static <A> Function2<Predicate<A>, Traversable<A>, Boolean> any()
  {
    return (condition, col) -> col.exists(condition);
  }

  public static <A> Function1<Traversable<A>, Boolean> any(Predicate<A> condition)
  {
    return Collection.<A>any().apply(condition);
  }

  public static <A> Function2<Predicate<A>, Traversable<A>, Boolean> all()
  {
    return (condition, col) -> col.forAll(condition);
  }

  public static <A> Function1<Traversable<A>, Boolean> all(Predicate<A> condition)
  {
    return Collection.<A>all().apply(condition);
  }

  public static <A> Function2<Predicate<A>, Traversable<A>, Boolean> none()
  {
    return Collection.<A>any().andThen(not());
  }

  public static <A> Function1<Traversable<A>, Boolean> none(Predicate<A> condition)
  {
    return Collection.<A>none().apply(condition);
  }

  public static <A> Function2<String, Traversable<A>, String> join()
  {
    return (separator, col) -> col.mkString(separator);
  }

  public static <A> Function1<Traversable<A>, String> join(String separator)
  {
    return Collection.<A>join().apply(separator);
  }

  public static <A> Function1<Traversable<A>, A> head()
  {
    return Traversable::head;
  }

  public static <A> Function1<Traversable<A>, A> last()
  {
    return Traversable::last;
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> tail()
  {
    return Traversable::tail;
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> distinct()
  {
    return Traversable::distinct;
  }

  public static <A> Function2<A, Traversable<A>, Traversable<A>> append()
  {
    return (val, col) -> List.ofAll(col).append(val);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> append(A val)
  {
    return Collection.<A>append().apply(val);
  }

  public static <A> Function2<A, Traversable<A>, Traversable<A>> prepend()
  {
    return (val, col) -> List.ofAll(col).prepend(val);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> prepend(A val)
  {
    return Collection.<A>prepend().apply(val);
  }

  public static <A> Function2<Traversable<A>, Traversable<A>, Traversable<A>> union()
  {
    return (col, otherCol) -> List.ofAll(col).appendAll(otherCol);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> union(Traversable<A> col)
  {
    return Collection.<A>union().apply(col);
  }

  public static <A> Function2<Traversable<A>, Traversable<A>, Traversable<A>> intersection()
  {
    return (col, otherCol) -> filter(col::contains).apply(otherCol);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> intersection(Traversable<A> col)
  {
    return Collection.<A>intersection().apply(col);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> reverse()
  {
    return col -> col.foldRight(List.of(), append());
  }

  public static <A> Function2<Integer, Traversable<A>, Traversable<A>> take()
  {
    return (num, col) -> col.take(num);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> take(Integer num)
  {
    return Collection.<A>take().apply(num);
  }

  public static <A> Function2<Integer, Traversable<A>, Traversable<A>> drop()
  {
    return (num, col) -> col.drop(num);
  }

  public static <A> Function1<Traversable<A>, Traversable<A>> drop(Integer num)
  {
    return Collection.<A>drop().apply(num);
  }

  public static <A> Function2<Integer, Traversable<A>, A> nth()
  {
    return (index, col) -> Collection.<A>last().compose(take(index + 1)).apply(col);
  }

  public static <A> Function1<Traversable<A>, A> nth(Integer index)
  {
    return Collection.<A>nth().apply(index);
  }
}
