package com.example.tickerwatchlist.datatypes;

import java.util.LinkedList;
import java.util.Arrays;
public class FixedLinkedList<T> extends LinkedList<T> {
    private final int maxSize;

    public FixedLinkedList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T element) {
        if (size() >= maxSize) removeFirst();
        return super.add(element);
    }

    //UNTESTED
    public T addDrop(T element) {
        super.add(element);
        return removeFirst();
    }
}