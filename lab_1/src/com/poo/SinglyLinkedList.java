package com.poo;

public class SinglyLinkedList {

    //first node
    SingleListNode head; //default value of an object -> null
    //last node
    SingleListNode tail; //default value of an object -> null
    //number of elements
    int numElements; //default value of an int -> 0

    SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.numElements = 0;
    }

    Object getFirst() { return this.head.getElement(); }

    Object getLast() { return this.tail.getElement(); }

    public Object get(int position) {
        SingleListNode currentNode = head;
        int i = 0;

        while(currentNode != null){
            if(i == position){
                return currentNode.getElement();
            }
            i++;
            currentNode = currentNode.nextNode;
        }
    }
}

