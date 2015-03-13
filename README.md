# Java List Helpers

Useful library to work with Java Lists.

## Maven Snippet

If you are using Maven, configure the following dependency in your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.bicoco</groupId>
  <artifactId>java-list-helpers</artifactId>
  <version>1.3</version>
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

With this library you can iterate like this:

```java
_.each(persons, new EachFunction<Person>() {
    public void each(Person person) {
        System.out.println(person.getName());
    }
});
```

or transform it to a list of a different type:

```java
List<Integer> ages = _.transform(persons, new TransformFunction<Person,Integer>() {
    public Integer transform(Person person) {
        return person.getAge();
    }
});
```

or select elements using a condition:

```java
List<Person> personsGreaterThan18YearsOld = _.select(persons, new ConditionFunction<Person>() {
    public boolean condition(Person person) {
        return person.getAge() > 18;
    }
});
```

or transform all of the elements to a single value which can be of any type:

```java
Integer result = _.reduce(people, 0, new ReduceFunction<Person, Integer>() {
    public Integer reduce(Integer memo, Person person) {
        return memo += person.getAge();
    }
});
```

There are many other features but for now you can see more examples in the `ListTest` class.
