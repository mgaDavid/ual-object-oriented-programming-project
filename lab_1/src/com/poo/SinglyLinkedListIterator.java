package com.poo;

/**
 * This is the class to iterate the Linked List
 * the methods will be have the purpose of traversing the Linked List
 * or returning to the beginning
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 25/10/2020
 */

public class SinglyLinkedListIterator {
    private SingleListNode head;
    private SingleListNode currentNode;

    /**
     * Method to create a Single List Iterator
     * We will use the head of the Single List Node
     * @param head of the Single List Node
     */
    public SinglyLinkedListIterator(SingleListNode head) {
        this.head = head;
        this.currentNode = head;
    }

    /**
     * Method to verify if the next node is null
     * @return True if the Current node we are traversing is not null or False when we get to the end
     */
    public boolean hasNext() {
        return currentNode != null;
    }

    /**
     * Method to traverse the Linked List and return the next element of the Linked List
     * If the List is empty, it will return null
     * @return next element of the Linked List
     */
    public Object next() {
        Object element = null;
        if (hasNext()){
            element = currentNode.getElement();
            currentNode = currentNode.getNext();
        }
        return element;
    }

    /**
     * Method to return to the beginning of the Linked List
     */
    public void rewind() {
        this.currentNode = this.head;
    }
}
