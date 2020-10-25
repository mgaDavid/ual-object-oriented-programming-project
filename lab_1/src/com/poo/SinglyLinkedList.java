package lab_1.src.com.poo;
import java.util.ArrayList;
/**
 *In this class we define the methods for insertion, search and removal
 * on the Linked List.
 * The Linked List will store the variables of the tail, head and number of elements
 *  @author Bruno Teodoro
 *  @author Diego Soares
 *  @author David Arco
 *  @version 1.0
 *  @since 25/10/2020
 *
 */

public class SinglyLinkedList {

    //first node
    SingleListNode head; //default value of an object -> null
    //last node
    SingleListNode tail; //default value of an object -> null
    //number of elements
    int numElements; //default value of an int -> 0

    /**
     * Constructor Method with head, tail and Number of Elements
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.numElements = 0;
    }

    /**
     * This method will return the first element of the Linked List
     * @return head
     */
    public Object getFirst() { return this.head.getElement(); }

    /**
     * This method will return the last element of the Linked List
     * @return tail
     */
    public Object getLast() { return this.tail.getElement(); }

    /**
     * This method will return the value of the node in a certain position
     * If the position is false, it will throw an error
     * @param position in the list
     * @return node
     * @throws ArrayIndexOutOfBoundsException if Linked List is empty
     */
    public SingleListNode getNode(int position) throws ArrayIndexOutOfBoundsException{
        if (position >= numElements){
            throw new ArrayIndexOutOfBoundsException("Indice fora do intervalo.");
        }
        SingleListNode currentNode = head;
        int i = 0;

        while (currentNode != null){
            if(i == position){
                break;
            }
            i++;
            currentNode = currentNode.nextNode;
        }
        return currentNode;
    }

    /**
     * Method that will call the getNode method based on the position of the node
     * @param position in the list
     * @return node
     */
    public Object get(int position) {
        return getNode(position).getElement();
    }

    /**
     * Method to insert one element in the first position of the Linked List
     * If the List is empty it will create the Linked List
     * If there is a element already in the first position, it will place the new
     * element in the first position and push the rest of the elements
     * @param element of the list
     */
    public void insertFirst(Object element){
        if (numElements == 0) {
            head = new SingleListNode(element, null);
            tail = this.head;
        }else{
            SingleListNode oldHead = head;
            head = new SingleListNode(element, oldHead);
        }
        this.numElements++;
    }

    /**
     * Method to insert one element in the last position of the Linked List
     * If the List is empty it will create the Link List
     * @param element of the list
     */
    public void insertLast(Object element){
        if (numElements == 0){
            this.insertFirst(element);
        } else{
            tail.nextNode = new SingleListNode(element, null);
            tail = tail.nextNode;
            numElements++;
        }
    }

    /**
     * Method to insert one element in a specific position of the list
     * The subsequent elements will be pushed
     * @param element of the list
     * @param position in the list
     */
    public void insert(Object element, int position){
        if (position == 0) {
            this.insertFirst(element);
        } else if (position == numElements){
            this.insertLast(element);
        }else{
            SingleListNode oldElement = getNode(position - 1);
            oldElement.nextNode = new SingleListNode(element, oldElement.nextNode);
            numElements++;
        }
    }

    /**
     * This is used to create an Array of Elements based on the Linked List
     * The purpose of this array is to have a useful display of the Linked List
     * in order to complete the Test Units
     * @return Array
     */
    public Object getArray(){
        ArrayList<Object> array = new ArrayList<>();
        if (numElements > 0) {
            SingleListNode currentNode = this.getNode(0);
            while (currentNode != null){
                array.add(currentNode.getElement());
                currentNode = currentNode.getNext();
            }
        }
        return array;
    }

    /**
     * This method is used to remove the first element of the Linked List
     * It will return True if we can remove the element from the list
     * If the Linked List is empty it will throw an error
     * @return true
     * @throws ArrayIndexOutOfBoundsException if Linked List is empty
     */
    public boolean removeFirst()  throws ArrayIndexOutOfBoundsException{
        if (head == null ) {
            throw new ArrayIndexOutOfBoundsException("Indice fora intervalo");
        } else {
            head = head.getNext();
            numElements--;
            return true;
        }
    }

    /**
     * This method is used to remove the last element of the Linked List
     * It will return True if we can remove the element from the list
     * If the Linked List is empty it will throw an error
     * @return True
     * @throws ArrayIndexOutOfBoundsException if Linked List is empty
     */
    public boolean removeLast()  throws ArrayIndexOutOfBoundsException{
        if (tail == null) {
            throw new ArrayIndexOutOfBoundsException("Indice fora intervalo");
        } else {
            if (numElements == 1) {
                head = null;
                tail = null;
            } else {
                SingleListNode oldElement = getNode(numElements - 2);
                oldElement.nextNode = null;
                tail = oldElement;
                if (numElements == 2){
                    head.nextNode = null;
                }
            }
            numElements--;
            return true;
        }
    }

    /**
     * This method is used to remove the one element of the Linked List
     * in a specific position
     * It will return True if we can remove the element from the list
     * If the Linked List is empty it will throw an error
     * @param position of the element in the list
     * @return True
     * @throws ArrayIndexOutOfBoundsException if Linked List is empty
     */
    public boolean remove(int position) throws ArrayIndexOutOfBoundsException{
        if (position >= numElements){
            throw new ArrayIndexOutOfBoundsException("Indice fora intervalo");
        } else if (position == 0){
            return this.removeFirst();
        } else if (numElements-1 == position){
            return this.removeLast();
        } else{
            SingleListNode oldElement = getNode(position-1);
            oldElement.nextNode = oldElement.nextNode.nextNode;
            numElements--;
            return true;
        }
    }

    public SinglyLinkedListIterator getIterator(){
        return new SinglyLinkedListIterator(head);
    }
}
