package stack;

import array.MyArray;

public class MyArrayStack extends MyArray {

    int top;
    MyArray arrayStack;

    MyArrayStack(){arrayStack = new MyArray();}

    MyArrayStack(int size) {arrayStack = new MyArray(size);}

    public void push(int data){
        if (isFull()) return;
        arrayStack.addElement(data);
    }

    public int pop(){
        if (isEmpty()) return MyArray.ERROR_NUM;
        return arrayStack.removeElement(--top);
    }

    public int peek(){
        if (isEmpty()) return MyArray.ERROR_NUM;
        return arrayStack.getElement(top-1);
    }

    public int getSize(){
        return top;
    }

    public boolean isFull(){
        return top == arrayStack.ARRAY_SIZE;
    }

    public boolean isEmpty(){
        return top == 0;
    }
}
