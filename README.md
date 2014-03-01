# Java List Helpers

It's a useful library for working with Java List.

## Maven Snippet

If you are using Maven, configure the following dependency in your pom.xml:

```xml
<dependency>
  <groupId>com.github.bicoco</groupId>
  <artifactId>java-list-helpers</artifactId>
  <version>1.1</version>
</dependency>
```

## How to use this

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

Use this library to list iterate:

```java
$.each(persons, new EachFunction<Person>() {
    public void each(Person person) {
        System.out.println(person.getName());
    }
});
```

or transform it in another type list:

```java
List<Integer> ages = $.transform(persons, new TransformFunction<Person,Integer>() {
    public Integer transform(Person person) {
        return person.getAge();
    }
});
```

or select elements using a condition:

```java
List<Person> personsGreaterThan18YearsOld = $.select(persons, new ConditionFunction<Person>() {
    public boolean condition(Person person) {
        return person.getAge() > 18;
    }
});
```

and more!!!
