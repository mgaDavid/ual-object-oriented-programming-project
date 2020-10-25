package lab_1.src.com.poo;

/**
 * In this Class we create the method for the Node of our Linked List
 * the main method besides other functional methods
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 25/10/2020
 */

public class SingleListNode {
    /**
     * This class defines one object of the Linked List
     * with a pointer to next element of the list
     * of the type SingleListNode
     */

    Object element;
    SingleListNode nextNode;

    /**
     * Main method of the Linked List that contains the objects
     * @param element of the list
     * @param nextNode on the list
     */
    public SingleListNode(Object element, SingleListNode nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    /**
     * Method that returns the object value of the Linked List
     * @return Object element
     */
    public Object getElement() { return this.element; }

    /**
     * Method that returns the pointer to the next element of the Linked List
     * @return next Object element
     */
    public SingleListNode getNext() {
        return this.nextNode;
    }

    /**
     * Method that sets te next element of the Linked List
     * @param node = next node on the list
     */
    public void setNext(SingleListNode node) {
        this.nextNode = node;
    }
}


class Program {
    /**
     * This is the main method which will construct the Linked List
     * and will use all the methods of insertion, search, remove
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        SinglyLinkedList newList = new SinglyLinkedList();
        //newList.remove(2);
        //newList.insert("B",0);
        //newList.insert("C",1);
        //newList.insertFirst("A");
        //newList.insertLast("C");
        //newList.insert("X",2);
        //newList.removeFirst();
        //newList.removeLast();
        //newList.remove(0);
        //newList.printList();
        //newList.insert("A",0);
        //newList.insert("B",1);
        //System.out.println(newList.getLast());
        //System.out.println(newList.getArray());
        //SingleListNode node1 = new SingleListNode("A", null);
        //SingleListNode node2 = new SingleListNode("B",null);
        //node1.setNext(node2);
        //System.out.println(node1.getNext().getElement());
    }
}
