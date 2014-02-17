/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 *
 * @author qin
 */
public class RunMemoryLeakStack {

    private static class Stack {

        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            return elements[--size];
        }

        public boolean isEmmpty() {
            return size == 0;
        }

        /**
         * Ensure space for at least one more element, roughly doubling the
         * capacity each time the array needs to grow.
         */
        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }

    }

    public static void main(String[] args) {
        Stack s = new Stack();
        try {
            for (int i = 0; i < 10000; i++) {
                s.push(i);

            }
            while (!s.isEmmpty()) {
                s.pop();
            }

            System.in.read();
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    }

}
