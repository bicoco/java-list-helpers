package com.github.bicoco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.bicoco.collections.functions.ConditionFunction;
import com.github.bicoco.collections.functions.EachFunction;
import com.github.bicoco.collections.functions.MapFunction;
import com.github.bicoco.collections.functions.ReduceFunction;
import com.github.bicoco.collections.functions.TransformFunction;

public class ListTest {

    List<String> strings = new ArrayList<String>();
    List<Integer> numbers = new ArrayList<Integer>();

    @Before
    public void setup() {
        strings = new ArrayList<String>(
                Arrays.asList("A", "B", "C", "D", "DA", "ABC")
        );
        numbers = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
        );
    }

    public @Test void eachListOfStrings() {
        final StringBuilder result = new StringBuilder();
        _.each(strings, new EachFunction<String>() {
            @Override
            public void each(String s) {
                if (s.startsWith("A")) {
                    result.append(s);
                }
            }
        });

        assertEquals("AABC", result.toString());
    }

    public @Test void mapListOfStrings() {
        List<String> result = _.map(strings, new MapFunction<String>() {
            @Override
            public String map(String s) {
                if (s.startsWith("A")) {
                    return s.concat("B");
                }
                return s;
            }
        });

        List<String> expectedStrings = Arrays.asList(
                "A", "B", "C", "D", "DA", "ABC");
        List<String> expectedResult = Arrays.asList(
                "AB", "B", "C", "D", "DA", "ABCB");

        assertEquals(expectedStrings, strings);
        assertEquals(expectedResult, result);
    }

    public @Test void selectValuesGreaterThan4InNumberList() {
        List<Integer> list = _.select(numbers, new ConditionFunction<Integer>() {
            @Override
            public boolean condition(Integer i) {
                return i < 4;
            }
        });

        List<Integer> expectedResult = Arrays.asList(1, 2, 3);
        List<Integer> expectedValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertEquals(expectedResult, list);
        assertEquals(expectedValues, numbers);
    }

    public @Test void rejectValuesGreaterThan4InNumberList() {
        List<Integer> result = _.reject(numbers, new ConditionFunction<Integer>() {
            @Override
            public boolean condition(Integer i) {
                return i < 4;
            }
        });

        List<Integer> expectedResult = Arrays.asList(4, 5, 6, 7, 8, 9);
        List<Integer> expectedValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertEquals(expectedResult, result);
        assertEquals(expectedValues, numbers);
    }

    public @Test void select$ValuesGreaterThan4InNumberList() {
        _.select$(numbers, new ConditionFunction<Integer>() {
            @Override
            public boolean condition(Integer i) {
                return i < 4;
            }
        });

        List<Integer> expected = Arrays.asList(1, 2, 3);

        assertEquals(expected, numbers);
    }

    public @Test void reject$ValuesGreaterThan4InNumberList() {
        _.reject$(numbers, new ConditionFunction<Integer>() {
            @Override
            public boolean condition(Integer i) {
                return i < 4;
            }
        });

        List<Integer> expected = Arrays.asList(4, 5, 6, 7, 8, 9);

        assertEquals(expected, numbers);
    }

    public @Test void compact$ValuesGreaterThan4InNumberList() {

        _.push(numbers, null, null, null);
        _.compact$(numbers);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertEquals(expected, numbers);
    }

    public @Test void map$ListOfStrings() {
        _.map$(strings, new MapFunction<String>() {
            @Override
            public String map(String s) {
                if (s.startsWith("A")) {
                    return s.concat("B");
                }
                return s;
            }
        });

        List<String> expected = Arrays.asList(
                "AB", "B", "C", "D", "DA", "ABCB");

        assertEquals(expected, strings);
    }

    public @Test void transformStringsInIntegers() {
        List<Integer> result = _.transform(strings,
                new TransformFunction<String, Integer>() {
                    @Override
                    public Integer transform(String s) {
                        return s.length();
                    }
                }
        );

        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 2, 3);

        assertEquals(expected, result);
    }

    public @Test void getFirstElement() {
        String s = _.first(strings);
        assertEquals("A", s);
    }

    public @Test void getLastElement() {
        String s = _.last(strings);
        assertEquals("ABC", s);
    }

    public @Test void invokeAtWithValidIndex() {
        String s = _.at(strings, 1);
        assertEquals("B", s);
    }

    public @Test void invokeAtWithInvalidIndex() {
        String s = _.at(strings, 10);
        assertNull(s);
    }

    public @Test void invokeFetchWithValidIndex() {
        String s = _.fetch(strings, 1);
        assertEquals("B", s);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invokeFetchWithInvalidIndex() {
        String s = _.fetch(strings, 10);
        assertNull(s);
    }

    public @Test void invokeFetchWithInvalidIndexAndDefaultValue() {
        String s = _.fetch(strings, 10, "maoe");
        assertEquals("maoe", s);
    }

    public @Test void chainMethodPush() {
        _.push(strings, "F")
         .push("G")
         .push("H");

        List<String> expected = Arrays.asList(
                "A", "B", "C", "D", "DA", "ABC", "F", "G", "H");
        assertEquals(expected, strings);
    }

    public @Test void pushMultipleElements() {
        _.push(strings, "F", "G", "H");
        List<String> expected = Arrays.asList(
                "A", "B", "C", "D", "DA", "ABC", "F", "G", "H");
        assertEquals(expected, strings);
    }

    public @Test void take() {
        List<Integer> list = _.take(numbers, 3);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, list);
    }

    public @Test void drop() {
        List<Integer> list = _.drop(numbers, 3);
        List<Integer> expected = Arrays.asList(4, 5, 6, 7, 8, 9);
        assertEquals(expected, list);
    }

    public @Test void isEmpty() {
        assertFalse(_.isEmpty(numbers));
    }

    public @Test void isNotEmpty() {
        assertTrue(_.isNotEmpty(numbers));
    }

    public @Test void size() {
        int count = _.size(numbers);
        assertEquals(9, count);
    }

    public @Test void count() {
        int count = _.count(numbers);
        assertEquals(9, count);
    }

    public @Test void countValuesGreaterThan4() {
        int count = _.count(numbers, new ConditionFunction<Integer>() {
            @Override
            public boolean condition(Integer val) {
                return val > 4;
            }
        });
        assertEquals(5, count);
    }
    
    public @Test void all(){
    	boolean result = _.all(numbers, new ConditionFunction<Integer>() {

            @Override
            public boolean condition(Integer t) {
                return t < 10;
            }

        });
    	assertTrue(result);
    }
    
    public @Test void any(){
    	boolean result = _.any(numbers, new ConditionFunction<Integer>() {

            @Override
            public boolean condition(Integer t) {
                return t == 5;
            }

        });
    	assertTrue(result);
    }
    
    public @Test void reduce() {
    	List<Integer> ages = Arrays.asList(10, 20, 30, 40, 50);
    	Integer expected = 150;
    	Integer result = _.reduce(ages, 0, new ReduceFunction<Integer, Integer>() {
            public Integer reduce(Integer memo, Integer age) {
                return memo += age;
            }

            ;
        });
    	assertEquals(expected, result);
    }
    
    public @Test void reduce2() {
    	ArrayList<Person> people = new ArrayList<Person>();
    	people.add(new Person("David", 10));
    	people.add(new Person("André", 20));
    	people.add(new Person("Fernando", 30));
    	people.add(new Person("Lucas", 40));

    	Integer expected = 100;
    	Integer result = _.reduce(people, 0, new ReduceFunction<Person, Integer>() {
            public Integer reduce(Integer memo, Person person) {
                return memo += person.getAge();
            }

            ;
        });
    	assertEquals(expected, result);
    }
    
    public @Test void reduce3() {
    	ArrayList<Person> people = new ArrayList<Person>();
    	people.add(new Person("David", 10));
    	people.add(new Person("André", 20));
    	people.add(new Person("Fernando", 30));
    	people.add(new Person("Lucas", 40));

    	String expected = "Names: David, André, Fernando, Lucas";
    	String result = _.reduce(people, "Names: ", new ReduceFunction<Person, String>() {
            public String reduce(String memo, Person person) {
                if ("Names: ".equals(memo)) return memo + person.getName();
                return memo + ", " + person.getName();
            }

            ;
        });
    	assertEquals(expected, result);
    }

    public @Test void readme() {
        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(new Person("David", 27));
        persons.add(new Person("André", 30));
        persons.add(new Person("Fernando", 25));
        persons.add(new Person("Lucas", 15));

        _.each(persons, new EachFunction<Person>() {
            public void each(Person person) {
                System.out.println(person.getName());
            }
        });

        List<Integer> ages = _.transform(persons, new TransformFunction<Person, Integer>() {
            public Integer transform(Person person) {
                return person.getAge();
            }
        });

        System.out.println(ages);

        List<Person> personsGreaterThan18YearsOld = _.select(persons, new ConditionFunction<Person>() {
            public boolean condition(Person person) {
                return person.getAge() > 18;
            }
        });

        _.each(personsGreaterThan18YearsOld, new EachFunction<Person>() {
            public void each(Person person) {
                System.out.println(person.getName());
            }
        });

    }

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

}
