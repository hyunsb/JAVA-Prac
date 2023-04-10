package linkedList;

public class MyListNode {

    private String data;
    MyListNode next;

    MyListNode(){
    }

    MyListNode(String data){
        this.data = data;
    }

    MyListNode(String data, MyListNode next){
        this.data = data;
        this.next = next;
    }

    public String getData(){
        return data;
    }
}
