package lab_1.src.com.poo;
import java.util.ArrayList;


public class SinglyLinkedList {

    //first node
    SingleListNode head; //default value of an object -> null
    //last node
    SingleListNode tail; //default value of an object -> null
    //number of elements
    int numElements; //default value of an int -> 0

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.numElements = 0;
    }

    public Object getFirst() { return this.head.getElement(); }

    public Object getLast() { return this.tail.getElement(); }

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

    public Object get(int position) {
        return getNode(position).getElement();
    }

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

    public void insertLast(Object element){
        if (numElements == 0){
            this.insertFirst(element);
        } else{
            tail.nextNode = new SingleListNode(element, null);
            tail = tail.nextNode;
            numElements++;
        }
    }

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

    public boolean removeFirst()  throws ArrayIndexOutOfBoundsException{
        if (head == null ) {
            throw new ArrayIndexOutOfBoundsException("Indice fora intervalo");
        } else {
            head = head.getNext();
            numElements--;
            return true;
        }
    }

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
