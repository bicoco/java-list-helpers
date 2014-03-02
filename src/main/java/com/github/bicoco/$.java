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
package com.github.bicoco;

import com.github.bicoco.collections.*;
import com.github.bicoco.collections.functions.*;

import java.util.List;

/**
 * This library is a collection of auxiliary methods for accessing,
 * searching and otherwise manipulating {@link java.util.List} objects.
 * The '$' character is inspired in jQuery library, and the methods
 * in the Array class of ruby language.
 *
 * This class is a wrapper for the methods of api.
 *
 * @since 1.0
 */
public class $ {

    // ------------------------------------------------------------------
    // Iterating Methods
    // ------------------------------------------------------------------

    /**
     * Execute a custom action for each element of list.
     * @param list list of elements to iterate
     * @param function function to execute in each element
     */
    public static <T> void each(List<T> list, EachFunction<T> function) {
        new ListHelper<T>(list).each(function);
    }

    /**
     * Return a new list of same type element, applying new value for each element.
     * @param list list of elements to iterate
     * @param function function to apply in each value that returns the new value
     * @return changed list of same type element
     */
    public static <T> List<T> map(List<T> list, MapFunction<T> function) {
        return new ListHelper<T>(list).map(function);
    }

    /**
     * Return a new list of another type element, applying a function for each element.
     * @param list list of elements to iterate
     * @param function function to apply in each element of list
     * @return list of elements transformed
     */
    public static <T,R> List<R> transform(List<T> list, TransformFunction<T,R> function) {
        return new ListHelper<T>(list).transform(function);
    }

    // ------------------------------------------------------------------
    // Selecting Methods
    // ------------------------------------------------------------------

    /**
     * Select all elements that condition returns true.
     * @param list list of elements to iterate
     * @param function Apply in each element and select if returns true.
     * @return list of elements that the condition is true.
     */
    public static <T> List<T> select(List<T> list, ConditionFunction<T> function) {
        return new ListHelper<T>(list).select(function);
    }

    /**
     * Select all elements that condition returns false.
     * @param list list of elements to iterate
     * @param function Apply in each element and select if returns true.
     * @return list of elements that the condition is false.
     */
    public static <T> List<T> reject(List<T> list, ConditionFunction<T> function) {
        return new ListHelper<T>(list).reject(function);
    }

    // ------------------------------------------------------------------
    // Modifying Methods
    // ------------------------------------------------------------------

    /**
     * Modifing list applying new value for each element.
     * @param list values list of elements
     * @param function function to apply in each value that returns the new value
     */
    public static <T> void map$(List<T> list, MapFunction<T> function) {
        new ListHelper<T>(list).map$(function);
    }

    /**
     * Select all elements that condition returns true.
     * @param list values list of elements
     * @param function Apply in each element and select if returns true.
     */
    public static <T> void select$(List<T> list, ConditionFunction<T> function) {
        new ListHelper<T>(list).select$(function);
    }

    /**
     * Select all elements that condition returns false.
     * @param list values list of elements
     * @param function Apply in each element and select if returns false.
     */
    public static <T> void reject$(List<T> list, ConditionFunction<T> function) {
        new ListHelper<T>(list).reject$(function);
    }

    /**
     * Remove null values in list
     * @param list values list of elements
     */
    public static <T> void compact$(List<T> list) {
        new ListHelper<T>(list).compact$();
    }

    /**
     * Adding multiple elements to the end of list.
     * @param list values list of elements
     * @param ts elements to push
     */
    public static <T> void push(List<T> list, T... ts) {
        new ListHelper<T>(list).push(ts);
    }

    /**
     * Adding element to the end of list.
     * @param list values list of elements
     * @param t element to push
     * @return ListHelper to chaining methods
     */
    public static <T> ListHelper<T> push(List<T> list, T t) {
        return new ListHelper<T>(list).push(t);
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
    public static <T> T at(List<T> list, int index) {
        return new ListHelper<T>(list).at(index);
    }

    /**
     * Identical to {@link List#get(int)}
     * @param list list of elements
     * @param index index of element
     * @return The element at index
     * @throws java.lang.IndexOutOfBoundsException
     */
    public static <T> T fetch(List<T> list, int index) {
        return new ListHelper<T>(list).fetch(index);
    }

    /**
     * Null-safe, get element at index, with default value.
     * @param list list of elements
     * @param index index of element
     * @param def Default value
     * @return element of list or default value
     */
    public static <T> T fetch(List<T> list, int index, T def) {
        return new ListHelper<T>(list).fetch(index, def);
    }

    /**
     * Get the first element of list.
     * @param list list of elements
     * @return the first element
     */
    public static <T> T first(List<T> list) {
        return new ListHelper<T>(list).first();
    }

    /**
     * Get the last element of list.
     * @param list list of elements
     * @return the last element
     */
    public static <T> T last(List<T> list) {
        return new ListHelper<T>(list).last();
    }

    /**
     * Get the first n elements of list
     * @param list values list of type T
     * @param n number of elements
     * @return List of the first n elements
     */
    public static <T> List<T> take(List<T> list, int n) {
        return new ListHelper<T>(list).take(n);
    }

    /**
     * Get the elements of list excluding the first n elements
     * @param list list of elements
     * @param n number of elements to exclude
     * @return list of the elements
     */
    public static <T> List<T> drop(List<T> list, int n) {
        return new ListHelper<T>(list).drop(n);
    }
    
    /**
     * Identical to {@link #find(List, ConditionFunction)}
     * Get the first element that pass the predicate or null if no value passes
     * @param function apply in each element 
     * @return the first element that pass on predicate
     */
    public static <T> T detect(List<T> list, ConditionFunction<T> function) {
    	for (T t : list) {
			if (function.condition(t)) {
				return t;
			}
		}
		return null;
    }
    
    /**
     * Identical to {@link #detect(List, ConditionFunction)}
     * {@inheritDoc #detect(List, ConditionFunction)}
     */
    public static <T> T find(List<T> list, ConditionFunction<T> function) {
    	return detect(list, function);
    }

    // ------------------------------------------------------------------
    // Information Methods
    // ------------------------------------------------------------------
    /**
     * Check if list is empty
     * @return true if list is null or size is 0.
     */
    public static <T> boolean isEmpty(List<T> list) {
        return new ListHelper<T>(list).isEmpty();
    }

    /**
     * Check if list is not empty
     * @return true if list is not null and size is greater than 0.
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return new ListHelper<T>(list).isNotEmpty();
    }

    /**
     * Identical to List#size()
     * @param list the list of elements
     * @return the size of list
     */
    public static <T> int size(List<T> list) {
        return new ListHelper<T>(list).size();
    }

    /**
     * Return size of list or 0 to empty list.
     * @return the size of list
     */
    public static <T> int count(List<T> list) {
        return new ListHelper<T>(list).count();
    }

    /**
     * Return size of list where function returns true, or 0 to empty list.
     * @param list the list of elements
     * @param function the condition to consider element in count
     * @return the size of list
     */
    public static <T> int count(List<T> list, ConditionFunction<T> function) {
        return new ListHelper<T>(list).count(function);
    }
    
    /**
     * Identical to {@link #all(List, ConditionFunction)}
     * Returns true if all of the values in the list pass the predicate truth test
     * @param function apply in each element and return false if one of them fail
     * @return true if all elements pass on test
     */
    public static <T> boolean every(List<T> list, ConditionFunction<T> function) {
    	return new ListHelper<T>(list).every(function);
    }
    
    /**
     * Identical to {@link #every(List, ConditionFunction)}
     * {@inheritDoc #every(List, ConditionFunction)}
     */
    public static <T> boolean all(List<T> list, ConditionFunction<T> function) {
    	return every(list, function);
    }
    
    /**
     * Identical to {@link #some(List, ConditionFunction)}
     * Returns true if any of the values in the list pass the predicate truth test
     * @param function apply in each element and return true if one of them pass
     * @return true if any elements pass on test
     */
    public static <T> boolean any(List<T> list, ConditionFunction<T> function) {
    	return new ListHelper<T>(list).any(function);
    }
    
    /**
     * Identical to {@link #any(List, ConditionFunction)}
     * {@inheritDoc #any(List, ConditionFunction)}
     */
    public static <T> boolean some(List<T> list, ConditionFunction<T> function) {
    	return any(list, function);
    }    

}
