package linkedList;

public class Main {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();

        linkedList.addElement("a");
        linkedList.addElement("b");
        linkedList.addElement("c");

        linkedList.insertElement(1, "d");
        linkedList.removeElement(1);

        System.out.println(linkedList.toString());
    }
}
