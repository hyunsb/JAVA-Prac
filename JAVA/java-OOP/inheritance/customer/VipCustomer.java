package inheritance.customer;

/**
 * <h3>매출에 더 많은 기여를 하는 단골 고객</h3>
 * 제품을 살때 10%를 할인해 줌<br>
 *
 * 보너스 포인트는 제품 가격의 5%를 적립해 줌<br>
 *
 * 담당 전문 상담원이 배정됨
 * */

public class VipCustomer extends Customer{

    private double salesRatio;
    private String agentID;

    public VipCustomer(String name) {
        super.name = name;
        bonusRatio = 0.05;
        salesRatio = 0.1;
        grade = "VIP";
    }

    @Override
    public String showInfo() {
        return name + "님의 \n등급: " + grade + "\n보너스 포인트: " + bonusPoint + "\n할인률: " + salesRatio*100 + "%";
    }

    public double getSalesRatio() {
        return salesRatio;
    }

    public void setSalesRatio(double salesRatio) {
        this.salesRatio = salesRatio;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }
}
