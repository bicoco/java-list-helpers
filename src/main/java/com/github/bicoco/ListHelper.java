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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListHelper<T> {

    private final List<T> list;

    /**
     *
     * @param list list of elements
     */
    public ListHelper(List<T> list) {
        this.list = list;
    }

    // ------------------------------------------------------------------
    // Iterating Methods
    // ------------------------------------------------------------------

    public void each($.Each<T> each) {
        for (T t : list) {
            each.each(t);
        }
    }

    // ------------------------------------------------------------------
    // Selecting Methods
    // ------------------------------------------------------------------

    public List<T> map($.Map<T> map) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : list) result.add(map.map(t));
        return result;
    }

    public List<T> select($.Condition<T> condition) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : list) {
            if (condition.condition(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<T> reject($.Condition<T> condition) {
        ArrayList<T> result = new ArrayList<T>();
        for (T t : list) {
            if (!condition.condition(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public <R> List<R> transform($.Transform<T,R> transform) {
        ArrayList<R> result = new ArrayList<R>();
        for (T t : list) {
            result.add(transform.transform(t));
        }
        return result;
    }

    // ------------------------------------------------------------------
    // Modifying Methods
    // ------------------------------------------------------------------

    public void map$($.Map<T> map) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T t = map.map(it.next());
            it.set(t);
        }
    }

    public void select$($.Condition<T> condition) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T t = it.next();
            if (!condition.condition(t)) {
                it.remove();
            }
        }
    }

    public void reject$($.Condition<T> condition) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T t = it.next();
            if (condition.condition(t)) {
                it.remove();
            }
        }
    }

    /**
     * Remove null values in list
     */
    public void compact$() {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
    }

    // ------------------------------------------------------------------
    // Accessing Methods
    // ------------------------------------------------------------------

    public T at(int index) {
        if (index >= list.size()) {
            return null;
        }
        else if (index < 0) {
            index = list.size() + index;
        }

        return list.get(index);
    }

    /**
     * Identical of {@link java.util.List#get(int)}
     * @param index index of element
     * @return Object T at param index
     * @throws IndexOutOfBoundsException
     */
    public T fetch(int index) {
        return list.get(index);
    }

    /**
     * Null-safe, defining the default value
     *
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

    public T first() {
        return at(0);
    }

    public T last() {
        return at(-1);
    }

    /**
     * Get the first n elements of list
     * @param n number of elements
     * @return List of the first n elements
     */
    public List<T> take(int n) {
        return list.subList(0, n);
    }

    public List<T> drop(int n) {
        return list.subList(n, list.size());
    }

    // ------------------------------------------------------------------
    // Information Methods
    // ------------------------------------------------------------------
    public boolean isEmpty() {
        return list == null || list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public boolean isNotEmpty() {
        return list != null && list.size() > 0;
    }

    public int count() {
        if (isNotEmpty()) {
            return size();
        }
        return 0;
    }

    public int count($.Condition<T> condition) {
        int count = 0;
        if (isNotEmpty()) {
            for (T t : list) {
                if (condition.condition(t)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int length() {
        return count();
    }

    // ------------------------------------------------------------------
    // Other Methods
    // ------------------------------------------------------------------

    public void insert(T ... ts) {
        for (T t : ts) {
            insert(t);
        }
    }

    public ListHelper<T> insert(T t) {
        this.list.add(t);
        return this;
    }

}
