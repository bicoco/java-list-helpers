/**
 * The MIT License (MIT)
 * Copyright (c) 2014 Anderson Davi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.bicoco.collections;

import com.github.bicoco.collections.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Helper methods to List Java Interface
 *
 * @since 1.0
 */
public class ListHelper<T> {

    private final List<T> list;

    /**
     * Default constructor.
     * @param list the list to execute operations
     */
    public ListHelper(List<T> list) {
        this.list = list;
    }

    // ------------------------------------------------------------------
    // Iterating Methods
    // ------------------------------------------------------------------

    /**
     * Execute a custom action for each element of list.
     * @param function function to execute in each element
     */
    public void each(EachFunction<T> function) {
        for (T t : list) {
            function.each(t);
        }
    }

    /**
     * Return a new list of same type element, applying new value for each element.
     * @param function function to apply in each value that returns the new value
     * @return changed list of same type element
     */
    public List<T> map(MapFunction<T> function) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : list) result.add(function.map(t));
        return result;
    }

    /**
     * Return a new list of another type element, applying a function for each element.
     * @param function function to apply in each element of list
     * @return list of elements transformed
     */
    public <R> List<R> transform(TransformFunction<T,R> function) {
        ArrayList<R> result = new ArrayList<R>();
        for (T t : list) {
            result.add(function.transform(t));
        }
        return result;
    }

    // ------------------------------------------------------------------
    // Selecting Methods
    // ------------------------------------------------------------------

    /**
     * Select all elements that condition returns true.
     * @param function Apply in each element and select if returns true.
     * @return list of elements that the condition is true.
     */
    public List<T> select(ConditionFunction<T> function) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : list) {
            if (function.condition(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Select all elements that condition returns false.
     * @param function Apply in each element and select if returns true.
     * @return list of elements that the condition is false.
     */
    public List<T> reject(ConditionFunction<T> function) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : list) {
            if (!function.condition(t)) {
                result.add(t);
            }
        }
        return result;
    }

    // ------------------------------------------------------------------
    // Modifying Methods
    // ------------------------------------------------------------------

    /**
     * Modifing list applying new value for each element.
     * @param function function to apply in each value that returns the new value
     */
    public void map$(MapFunction<T> function) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T t = function.map(it.next());
            it.set(t);
        }
    }

    /**
     * Select all elements that condition returns true.
     * @param function Apply in each element and select if returns true.
     */
    public void select$(ConditionFunction<T> function) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T t = it.next();
            if (!function.condition(t)) {
                it.remove();
            }
        }
    }

    /**
     * Select all elements that condition returns false.
     * @param function Apply in each element and select if returns false.
     */
    public void reject$(ConditionFunction<T> function) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T t = it.next();
            if (function.condition(t)) {
                it.remove();
            }
        }
    }

    /**
     * Remove null values of list.
     */
    public void compact$() {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
    }

    /**
     * Adding multiple elements to the end of list.
     * @param ts elements to push
     */
    public void push(T... ts) {
        for (T t : ts) {
            push(t);
        }
    }

    /**
     * Adding element to the end of list.
     * @param t element to push
     * @return ListHelper to chaining methods
     */
    public ListHelper<T> push(T t) {
        this.list.add(t);
        return this;
    }

    // ------------------------------------------------------------------
    // Accessing Methods
    // ------------------------------------------------------------------

    /**
     * Return the element at index. Returns null if index is
     * out of range. Negative index counts from the end of list.
     * @param index index of element
     * @return Element at the index
     */
    public T at(int index) {
        if (index < 0) {
            index = list.size() + index;
        }

        if (index >= list.size()) {
            return null;
        }

        return list.get(index);
    }

    /**
     * Identical to {@link java.util.List#get(int)}.
     * @param index index of element
     * @return Object T at param index
     * @throws IndexOutOfBoundsException
     */
    public T fetch(int index) {
        return list.get(index);
    }

    /**
     * Null-safe, defining the default value.
     * @param index index of element
     * @param def Default value
     * @return element of list or default value
     */
    public T fetch(int index, T def) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            return def;
        }
    }

    /**
     * Get the first element of list.
     * @return the first element
     */
    public T first() {
        return at(0);
    }

    /**
     * Get the last element of list.
     * @return the last element
     */
    public T last() {
        return at(-1);
    }

    /**
     * Get the first n elements of list.
     * @param n number of elements
     * @return List of the first n elements
     */
    public List<T> take(int n) {
        return list.subList(0, n);
    }

    /**
     * Get the elements of list excluding the first n elements.
     * @param n number of elements to exclude
     * @return list of the elements
     */
    public List<T> drop(int n) {
        return list.subList(n, list.size());
    }

    // ------------------------------------------------------------------
    // Information Methods
    // ------------------------------------------------------------------

    /**
     * Check if list is empty
     * @return true if list is null or size is 0.
     */
    public boolean isEmpty() {
        return list == null || list.size() == 0;
    }

    /**
     * Check if list is not empty
     * @return true if list is not null and size is greater than 0.
     */
    public boolean isNotEmpty() {
        return list != null && list.size() > 0;
    }

    /**
     * Identical to List#size()
     * @return the size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Return size of list or 0 to empty list.
     * @return the size of list
     */
    public int count() {
        if (isNotEmpty()) {
            return size();
        }
        return 0;
    }

    /**
     * Return size of list where function returns true, or 0 to empty list.
     * @param function the condition to consider element in count
     * @return the size of list
     */
    public int count(ConditionFunction<T> function) {
        int count = 0;
        if (isNotEmpty()) {
            for (T t : list) {
                if (function.condition(t)) {
                    count += 1;
                }
            }
        }
        return count;
    }
    
    /**
     * Returns true if all of the values in the list pass the predicate truth test
     * @param function apply in each element and return false if one of them fail
     * @return true if all elements pass on test
     */
	public boolean all(ConditionFunction<T> function) {
		for (T t : list) {
			if (!function.condition(t)) {
				return false;
			}
		}
		return true;
	}

    /**
     * Returns true if any of the values in the list pass the predicate truth test
     * @param function apply in each element and return true if one of them pass
     * @return true if any elements pass on test
     */
	public boolean any(ConditionFunction<T> function) {
		for (T t : list) {
			if (function.condition(t)) {
				return true;
			}
		}
		return true;
	}
	
	public <R> R reduce(R defaultValue, ReduceFunction<T, R> function) {
		for (T t : list) {
			defaultValue = function.reduce(defaultValue, t);
		}
		return defaultValue;
	}

}
