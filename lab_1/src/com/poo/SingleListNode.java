//Comentario do Diego para testar o pull request com o Bruno!
package lab_1.src.com.poo;


public class SingleListNode {
    Object element;
    SingleListNode nextNode;

    public SingleListNode(Object element, SingleListNode nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    public Object getElement() { return this.element; }

    public SingleListNode getNext() {
        return this.nextNode;
    }

    public void setNext(SingleListNode node) {
        this.nextNode = node;
    }
}


class Program {
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
        SingleListNode node1 = new SingleListNode("A", null);
        SingleListNode node2 = new SingleListNode("B",null);
        node1.setNext(node2);
        System.out.println(node1.getNext().getElement());
    }
}
