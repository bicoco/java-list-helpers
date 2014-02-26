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

import java.util.*;

/**
 * This library is a collection of auxiliary methods for accessing,
 * searching and otherwise manipulating {@link java.util.List} objects.
 * The '$' character is inspired in jQuery library, and the methods
 * in the Array class of ruby language.
 */
public class $ {

    // ------------------------------------------------------------------
    // Auxiliary Interfaces for Methods
    // ------------------------------------------------------------------

    public static interface Map<T> {
        T map(T t);
    }

    public static interface Each<T> {
        void each(T t);
    }

    public static interface Condition<T> {
        boolean condition(T t);
    }

    public static interface Transform<T,R> {
        R transform(T t);
    }

    // ------------------------------------------------------------------
    // Iterating Methods
    // ------------------------------------------------------------------

    public static <T> void each(List<T> list, Each<T> each) {
        new ListHelper<T>(list).each(each);
    }

    // ------------------------------------------------------------------
    // Selecting Methods
    // ------------------------------------------------------------------

    public static <T> List<T> map(List<T> list, Map<T> map) {
        return new ListHelper<T>(list).map(map);
    }

    public static <T> List<T> select(List<T> list, Condition<T> condition) {
        return new ListHelper<T>(list).select(condition);
    }

    public static <T> List<T> reject(List<T> list, Condition<T> condition) {
        return new ListHelper<T>(list).reject(condition);
    }

    public static <T,R> List<R> transform(List<T> list, Transform<T,R> transform) {
        return new ListHelper<T>(list).transform(transform);
    }

    // ------------------------------------------------------------------
    // Modifying Methods
    // ------------------------------------------------------------------

    public static <T> void map$(List<T> list, Map<T> map) {
        new ListHelper<T>(list).map$(map);
    }

    public static <T> void select$(List<T> list, Condition<T> condition) {
        new ListHelper<T>(list).select$(condition);
    }

    public static <T> void reject$(List<T> list, Condition<T> condition) {
        new ListHelper<T>(list).reject$(condition);
    }

    /**
     * Remove null values in list
     * @param list values list of type T
     */
    public static <T> void compact$(List<T> list) {
        new ListHelper<T>(list).compact$();
    }

    // ------------------------------------------------------------------
    // Accessing Methods
    // ------------------------------------------------------------------

    public static <T> T at(List<T> list, int index) {
        return new ListHelper<T>(list).at(index);
    }

    /**
     * Identical of {@link List#get(int)}
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
     *
     * @param list list of elements
     * @param index index of element
     * @param def Default value
     * @return element of list or default value
     */
    public static <T> T fetch(List<T> list, int index, T def) {
        return new ListHelper<T>(list).fetch(index, def);
    }

    public static <T> T first(List<T> list) {
        return new ListHelper<T>(list).first();
    }

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

    // ------------------------------------------------------------------
    // Information Methods
    // ------------------------------------------------------------------
    public static <T> boolean isEmpty(List<T> list) {
        return new ListHelper<T>(list).isEmpty();
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return new ListHelper<T>(list).isNotEmpty();
    }

    public static <T> int size(List<T> list) {
        return new ListHelper<T>(list).size();
    }

    public static <T> int count(List<T> list) {
        return new ListHelper<T>(list).count();
    }

    public static <T> int count(List<T> list, Condition<T> condition) {
        return new ListHelper<T>(list).count(condition);
    }

    public static <T> int length(List<T> list) {
        return new ListHelper<T>(list).length();
    }

    // ------------------------------------------------------------------
    // Other Methods
    // ------------------------------------------------------------------

    public static <T> void insert(List<T> list, T ... ts) {
        new ListHelper<T>(list).insert(ts);
    }

    public static <T> ListHelper<T> insert(List<T> list, T t) {
        return new ListHelper<T>(list).insert(t);
    }

}
