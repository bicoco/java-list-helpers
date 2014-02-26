java-list-helpers
=================

It's a useful library for working with Java List. How to use this:

Suppose a simple class `Person`

```java
public class Person {
  private String name;
  private Integer age;
  
  public Person(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
  
  public String getName() {
    return name;
  }
  
  public Integer getAge() {
    return age;
  }
}
```
and a `List` of `Person`


```java
ArrayList<Person> persons = new ArrayList<Person>();
persons.add(new Person("David", 27));
persons.add(new Person("André", 30));
persons.add(new Person("Fernando", 25));
persons.add(new Person("Lucas", 15));
```

Use this library to:

Iterate for each:

```java
$.each(persons, new $.Each<Person>() {
  public void each(Person person) {
    System.out.println(person.getName());
  }
});
```

or transform it to a list of ages:

```java
ArrayList<Integer> ages = $.transform(persons, new $.Transform<Person,Integer>() {
  public Integer transform(Person person) {
    return person.getAge();
  }
});
```

or select persons if age > 18:

```java
ArrayList<Person> personsGreaterThan18YearsOld = $.select(persons, new $.Condition<Person,Integer>() {
  public boolean condition(Person person) {
    return person.getAge() > 18;
  }
});
```

and more!!!
