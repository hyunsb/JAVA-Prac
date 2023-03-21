## 캡슐화 (Encapsulation)

캡슐화는 클래스의 구현 세부 정보를 외부에서 숨기고, 오직 public 인터페이스만 노출하는 것을 말한다. 이렇게 함으로써, 클래스의 내부 구현을 변경하더라도 외부 코드에 영향을 미치지 않도록 할 수 있다.

```java
public class BankAccount {
    private int balance;

    public void setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    public int getBalance() {
        return balance;
    }
}

```

위의 코드는 계좌 잔액을 캡슐화하기 위한 예시이다.

balance 필드는 private으로 선언되어 있으며, 이를 직접적으로 접근할 수 없다. 대신, setBalance()와 getBalance() 메서드를 통해 간접적으로 접근할 수 있다.