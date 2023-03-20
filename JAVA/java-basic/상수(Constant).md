## 상수(Constant)

자바에서 상수를 사용하는 방법은 두 가지가 있다.

첫 번째는 `static final` 키워드를 사용하는 방법이며, 두 번째는 `enum`을 사용하는 방법이다.

`static final` 키워드를 사용하는 방법은 다음과 같다.

```java
public static final int MAX_VALUE = 100;

```

`MAX_VALUE`는 `public`, `static`, `final` 키워드를 사용하여 정의되었다. 이렇게 정의된 상수는 클래스의 인스턴스변수와는 달리 값을 변경할 수 없으며, 클래스 내부에서 사용할 수 있는 상수이다.

`enum`을 사용하는 방법은 다음과 같다.

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

```

위와 같이 `enum`을 정의하면, `Day`라는 이름의 열거형이 정의된다. `enum`을 사용하면 상수를 그룹핑할 수 있으며, 각 상수에는 이름과 값이 할당된다. 각 상수는 `Day.MONDAY`, `Day.TUESDAY`와 같이 사용할 수 있다.