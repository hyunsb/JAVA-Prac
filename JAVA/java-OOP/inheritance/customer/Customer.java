package inheritance.customer;

/**
 * <h3>일반 고객(Customer) 클래스 구현</h3>
 * 고객의 속성 : 고객 아이디, 고객 이름, 고객 등급, 보너스 포인트, 보너스 포인트 적립비율<br>
 * 일반 고객의 경우 물품 구매시 1%의 보너스 포인트 적립
 * */

public class Customer {

    private int id;
    protected String name;
    protected String grade;

    int bonusPoint;
    double bonusRatio;

    public Customer(){}

    public Customer(String name) {
        this.name = name;
        grade = "SILVER";
        bonusRatio = 0.01;
    }

    public int calcPrice(int price){
        bonusPoint += price * bonusRatio;
        return price;
    }

    public String showInfo(){
        return name + "님의 \n등급: " + grade + "\n보너스 포인트: " + bonusPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
