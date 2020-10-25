package lab_1.src.com.poo.teste;

import lab_1.src.com.poo.SinglyLinkedList;
import lab_1.src.com.poo.SinglyLinkedListIterator;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListIteratorTest {

    @Test
    void hasNext() {
        SinglyLinkedList list = new SinglyLinkedList();
        SinglyLinkedListIterator iterator = list.getIterator();
        assertFalse(iterator.hasNext());
        list.insert("A",0);
        list.insert(1,1);
        iterator = list.getIterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    void next() {
        SinglyLinkedList list = new SinglyLinkedList();
        SinglyLinkedListIterator iterator = list.getIterator();
        assertNull(iterator.next());
        list.insert("A",0);
        list.insert("B",1);
        iterator = list.getIterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertNull(iterator.next());
    }

    @Test
    void rewind() {
        SinglyLinkedList list = new SinglyLinkedList();
        SinglyLinkedListIterator iterator = list.getIterator();
        assertNull(iterator.next());
        iterator.rewind();
        assertNull(iterator.next());
        list.insert("A",0);
        list.insert("B",1);
        iterator = list.getIterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertNull(iterator.next());
        iterator.rewind();
        assertEquals("A", iterator.next());
    }
}
