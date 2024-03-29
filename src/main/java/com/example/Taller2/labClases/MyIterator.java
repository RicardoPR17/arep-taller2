package com.example.Taller2.labClases;

import java.util.Iterator;

public class MyIterator<E> implements Iterator<E> {

    private MyNode<E> next;

    public MyIterator(MyNode<E> next) {
        this.next = next;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public E next() {
        MyNode<E> currentNext = next;
        if (next != null)
            this.next = next.next();
        return currentNext.getValue();
    }

}
