package com.markjgill.functional;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;

public class Object
{
  public static <A> Function2<A, A, Boolean> equalTo()
  {
    return java.lang.Object::equals;
  }

  public static <A> Function1<A, Boolean> equalTo(A obj)
  {
    return Object.<A> equalTo().apply(obj);
  }

  public static <A, B> Function2<Class<A>, B, Boolean> isA()
  {
    return (clazz, obj) -> clazz.equals(obj.getClass());
  }

  public static <A, B> Function1<B, Boolean> isA(Class<A> clazz)
  {
    return Object.<A, B> isA().apply(clazz);
  }

  public static <A, B> Function2<Function1<A, B>, A, B> prop()
  {
    return Function1::apply;
  }

  public static <A, B> Function1<A, B> prop(Function1<A, B> fn)
  {
    return Object.<A, B>prop().apply(fn);
  }

  public static <A, B> Function3<Function1<A, B>, B, A, B> propOr()
  {
    return (fn, defaultVal, obj) -> Logic.defaultTo(defaultVal)
        .compose(prop(fn))
        .apply(obj);
  }

  public static <A, B> Function1<A, B> propOr(Function1<A, B> fn, B defaultVal)
  {
    return Object.<A, B>propOr().apply(fn, defaultVal);
  }
}
