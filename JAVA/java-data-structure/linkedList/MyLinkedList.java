package linkedList;

public class MyLinkedList {

    private MyListNode head;
    private int count;

    MyLinkedList(){}

    public MyListNode addElement(String data){
        MyListNode newNode = new MyListNode(data);

        if(head == null) head = newNode;
        else {
            MyListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        count++;
        return newNode;
    }

    public MyListNode insertElement(int position, String data){
        if (position < 0) return null;
        if (position > count) return null;

        MyListNode newNode = new MyListNode(data);

        if (position == count) return addElement(data);
        if (position == 0) {
            newNode.next = head;
            head = newNode;

        } else {
            MyListNode temp = searchNodeFromPosition(position);
            newNode.next = temp.next;
            temp.next = newNode;
        }

        count++;
        return newNode;
    }

    public MyListNode removeElement(int position){
        if (position < 0) return null;
        if (position > count) return null;

        MyListNode temp = head;
        if (position == 0) head = head.next;
        else {
            MyListNode prev = null;
            for (int i = 0; i < position; i++) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = temp.next;
        }

        count--;
        return temp;
    }

    private MyListNode searchNodeFromPosition(int position){
        MyListNode temp = head;
        for (int i = 0; i < position-1; i++) temp = temp.next;
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder listInfo = new StringBuilder();
        MyListNode temp = head;
        int cnt = 0;

        while (temp.next != null){
            listInfo.append(cnt++).append(": ").append(temp.getData()).append("\n");
            temp = temp.next;
        }


        return listInfo.append(cnt++).append(": ").append(temp.getData()).append("\n").toString();
    }

    public int getCount() {
        return count;
    }

    public MyListNode getHead() {
        return head;
    }
}
