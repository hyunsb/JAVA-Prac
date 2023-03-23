package inheritance.customer;

/**
 * <h3>멤버십 시나리오</h3>
 *
 * 회사에서 고객 정보를 활용한 맞춤 서비스를 하기 위해 일반고객(Customer)과
 * 이보다 충성도가 높은 우수고객(VIPCustomer)에 따른 서비스를 제공하고자 함
 * <br>
 * 물품을 구매 할때 적용되는 할인율과 적립되는 보너스 포인트의 비율이 다름
 * 여러 멤버십에 대한 각각 다양한 서비스를 제공할 수 있음
 * 멤버십에 대한 구현을 클래스 상속을 활용하여 구현해보기
 * */

public class CustomerTest {
    public static void main(String[] args) {

        Customer customerTom = new Customer("Tom");

        VipCustomer customerJack = new VipCustomer("Jack");
        Customer customerAnna = new VipCustomer("Anna");

        System.out.println(customerTom.showInfo());
        System.out.println("---------------------");
        System.out.println(customerJack.showInfo());
        System.out.println("---------------------");
        System.out.println(customerAnna.showInfo());


        customerJack.setAgentID("agentTest");
//        customerAnna.setAgentId("agentTest");
    }
}
