package com.markjgill.functional;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectTest
{
  @Test
  void equalTo()
  {
    assertThat(Object.equalTo("Hello").apply("Hello")).isTrue();
    assertThat(Object.equalTo("HelloWorld").apply("Hello")).isFalse();

    assertThat(Object.equalTo(5).apply(5)).isTrue();
    assertThat(Object.equalTo(5).apply(1)).isFalse();

    assertThat(Object.equalTo(new TestObject("Hello")).apply(new TestObject("Hello"))).isTrue();
    assertThat(Object.equalTo(new TestObject("Hello")).apply(new TestObject("World"))).isFalse();

  }

  @Test
  void isA()
  {
    assertThat(Object.isA(String.class).apply("Hello")).isTrue();
    assertThat(Object.isA(String.class).apply(5)).isFalse();

    assertThat(Object.isA(Integer.class).apply("Hello")).isFalse();
    assertThat(Object.isA(Integer.class).apply(5)).isTrue();
  }

  @Test
  void prop()
  {
    assertThat(Object.prop(TestObject::getName).apply(new TestObject("HelloWorld"))).isEqualTo("HelloWorld");
    assertThat(Object.prop(TestObject::getName).apply(new TestObject(null))).isNull();
  }

  @Test
  void propOr()
  {
    assertThat(Object.propOr(TestObject::getName, "Hello").apply(new TestObject("HelloWorld"))).isEqualTo("HelloWorld");
    assertThat(Object.propOr(TestObject::getName, "Hello").apply(new TestObject(null))).isEqualTo("Hello");
  }

  private static class TestObject
  {
    private final String name;

    public TestObject(String name)
    {
      this.name = name;
    }

    public String getName()
    {
      return name;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      TestObject that = (TestObject) o;
      return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(name);
    }
  }
}
