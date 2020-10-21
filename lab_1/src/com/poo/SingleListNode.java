package com.poo;

public class SingleListNode {
    Object element;
    SingleListNode nextNode;

    SingleListNode(Object element, SingleListNode nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    Object getElement() {
        return this.element;
    }

    SingleListNode getNext() {
        return this.nextNode;
    }

    void setNext(SingleListNode node) {
        this.element = node;
    }
}

class Program {
    public static void main(String[] args) {
        SingleListNode node = new SingleListNode("POO", null);
        System.out.println(node.getElement());
    }
}
