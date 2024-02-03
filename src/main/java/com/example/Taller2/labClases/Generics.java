package com.example.Taller2.labClases;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Generics<E> {
    public static void main(String[] args) {
        List<Integer> intList = new MyLinkedList<Integer>();
        intList.add(0);
        intList.add(1);
        Integer x = intList.iterator().next();

        printCollectionGeneric(intList);

        // List<String> ls = new ArrayList<>();
        // List<Object> lo = ls; No se puede

        String[] arrayString = { "arep", "patata" };
        List<String> linkedlistStrings = new LinkedList<>();

        fromArrayToCollection(arrayString, linkedlistStrings);
    }

    static void printCollection(Collection c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); k++) {
            System.out.println(i.next());
        }
    }

    static void printCollectionGeneric(Collection<?> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }

    // Método con tipo paramétrico
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // Correct
        }
    }
}
