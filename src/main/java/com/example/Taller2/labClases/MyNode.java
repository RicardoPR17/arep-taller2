package com.example.Taller2.labClases;

public class MyNode<E> {
    private E value;
    private MyNode<E> next;

    public MyNode(E value) {
        this.value = value;
        this.next = null;
    }

    public E getValue() {
        return this.value;
    }

    public MyNode<E> next() {
        return this.next;
    }

    public void setNext(MyNode<E> next) {
        this.next = next;
    }
}
