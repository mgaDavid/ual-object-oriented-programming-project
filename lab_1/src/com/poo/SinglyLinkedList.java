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

    public SingleListNode getNode(int position) {
        if(position >= numElements){
            throw new Exception("Fora do intervalo",ArrayIndexOutOfBoundsException);
        }
        SingleListNode currentNode = head;
        int i = 0;

        while(currentNode != null){
            if(i == position){
                break;
            }
            i++;
            currentNode = currentNode.nextNode;
        }
        return currentNode;
    }

    public Object get(int position) {
        SingleListNode element = getNode(position);
        return element.getElement();
    }

    public void insertFirst(Object element){
        if(numElements == 0) {
            head = new SingleListNode(element, null);
            tail = this.head;
        }else{
            SingleListNode oldHead = head;
            head = new SingleListNode(element, oldHead);
        }
        this.numElements++;
    }

    public void insertLast(Object element){
        if(numElements == 0){
            insertFirst(element);
        }else{
            SingleListNode newListNode = new SingleListNode(element, null);
            tail.nextNode = newListNode;
            tail = newListNode;
            numElements++;
        }
    }

    public void insert(Object element, int position){
        try{
            SingleListNode oldElement = getNode(position);
            SingleListNode newElement = new SingleListNode(element, oldElement.nextNode);
            oldElement.nextNode = newElement;
            numElements++;
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
