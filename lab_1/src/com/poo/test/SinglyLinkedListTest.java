package com.poo.test;

import com.poo.SinglyLinkedList;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void getFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertFirst("A");
        assertEquals("A", list.getFirst());
    }

    @Test
    void getLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertFirst("A");
        assertEquals("A", list.getLast());
        list.insertLast("B");
        assertEquals("B", list.getLast());
    }

    @Test
    void getNode() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.getNode(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.getNode(10));
        list.insertFirst("A");
        assertEquals("A", list.getNode(0).getElement());
        assertNull(list.getNode(0).getNext());
        list.insertLast("B");
        assertEquals("B", list.getNode(1).getElement());
        assertNull(list.getNode(1).getNext());
        assertEquals("B", list.getNode(0).getNext().getElement());
    }

    @Test
    void get() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(10));
        list.insertFirst("A");
        assertEquals("A", list.get(0));
        list.insertFirst("B");
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
    }

    @Test
    void insertFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertFirst("A");
        assertEquals("A", list.get(0));
        list.insertFirst("B");
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
    }

    @Test
    void insertLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertLast("A");
        assertEquals("A", list.get(0));
        list.insertLast("B");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void insert() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.insert("C", 10));
        list.insert("A",0);
        assertEquals("A", list.get(0));
        list.insert("B",1);
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        list.insert("X",1);
        assertEquals("A", list.get(0));
        assertEquals("X", list.get(1));
        assertEquals("B", list.get(2));
    }

    @Test
    void getArray() {
        SinglyLinkedList list = new SinglyLinkedList();
        ArrayList<Object> array = new ArrayList<>();
        assertEquals(array, list.getArray());
        list.insert("A",0);
        list.insert(1,1);
        list.insert("C",2);
        array.add('A');
        array.add(1);
        array.add('C');
        assertEquals(array.toString(), list.getArray().toString());
    }

    @Test
    void removeFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.removeFirst());
        list.insert("A",0);
        assertTrue(list.removeFirst());
    }

    @Test
    void removeLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.removeLast());
        list.insert("A",0);
        list.insert("B",1);
        assertEquals("B",list.getLast());
        System.out.println(list.getArray());
        assertTrue(list.removeLast());
        assertEquals("A",list.getLast());

    }

    @Test
    void remove() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(10));
        list.insert("A",0);
        list.insert("B",1);
        list.insert("C",2);
        assertTrue(list.remove(1));
        assertEquals("C",list.get(1));
    }
}
