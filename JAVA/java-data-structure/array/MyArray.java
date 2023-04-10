package array;

public class MyArray {

    int[] intArr;
    int count;

    public int ARRAY_SIZE;
    public static final int ERROR_NUM = -999999999;

    public MyArray(){
        ARRAY_SIZE = 10;
        intArr = new int[ARRAY_SIZE];
    }

    public void addElement(int num){
        if (count >= ARRAY_SIZE) {
            System.out.println("not enough memory");
            return;
        }

        intArr[count++] = num;
    }

    public void insertElement(int position, int num){

        if (position >= ARRAY_SIZE) {
            System.out.println("not enough memory");
            return;
        }

        if (position < 0 || position > count){
            System.out.println("insert Error");
            return;
        }

        for (int i = count-1; i >= position; i--)
            intArr[i+1] = intArr[i];

        intArr[position] = num;
        count++;
    }

    public int removeElement(int position){
        if (isEmpty()) return ERROR_NUM;
        if (position >= count) return ERROR_NUM;
        if (position < 0) return ERROR_NUM;

        for (int i = position; i < count; i++)
            intArr[i] = intArr[i+1];

        count--;
        return position;
    }

    private boolean isEmpty(){
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuilder arrInfo = new StringBuilder();
        for (int num : intArr) arrInfo.append(num).append(" ");
        return arrInfo.toString();
    }
}

class Main{
    public static void main(String[] args) {
        MyArray myArray = new MyArray();

        myArray.addElement(1);
        myArray.addElement(2);
        myArray.addElement(3);
        myArray.addElement(4);
        myArray.addElement(5);
        myArray.removeElement(3);
        myArray.insertElement(3, 4);

        System.out.println(myArray);
    }
}