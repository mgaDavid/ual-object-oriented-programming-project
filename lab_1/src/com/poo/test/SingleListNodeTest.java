package com.poo.test;

import com.poo.SingleListNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingleListNodeTest {

    @Test
    void getElement() {
        SingleListNode node = new SingleListNode("A",null);
        assertEquals("A",node.getElement());
    }

    @Test
    void getNext() {
        SingleListNode node2 = new SingleListNode("B",null);
        assertNull(node2.getNext());
        SingleListNode node1 = new SingleListNode("A", node2);
        assertEquals(node2, node1.getNext());
    }

    @Test
    void setNext() {
        SingleListNode node1 = new SingleListNode("A", null);
        SingleListNode node2 = new SingleListNode("B",null);
        assertNull(node1.getNext());
        node1.setNext(node2);
        assertEquals(node2, node1.getNext());
    }
}
