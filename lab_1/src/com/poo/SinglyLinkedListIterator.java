package com.poo;

public class SinglyLinkedListIterator {
    private SingleListNode head;
    private SingleListNode currentNode;

    public SinglyLinkedListIterator(SingleListNode head) {
        this.head = head;
        this.currentNode = head;
    }

    public boolean hasNext() {
        return currentNode != null;
    }

    public Object next() {
        Object element = currentNode.getElement();
        currentNode = currentNode.getNext();
        return element;
    }

    public void rewind() {
        this.currentNode = this.head;
    }
}
