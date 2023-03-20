## Java 14 - Switch Expression

Java 14부터는 `switch`문에서 새로운 표현식을 지원한다. 이를 `switch expression`이라고 한다. `switch expression`은 `switch`문의 결과값을 변수에 할당할 수 있으며, 표현식으로 사용할 수 있다.

```java
String result = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
    default -> throw new IllegalStateException("Invalid day: " + day);
};

```

위 코드에서 `switch`문을 실행한 결과값이 `result` 변수에 할당된다. `switch`문에서는 화살표(`->`)를 사용하여 각 `case`에 대한 결과값을 지정할 수 있다. `default`는 모든 `case`에 해당하지 않을 경우 실행되는 블록을 지정한다.

```java
public static void main(String[] args) {
        int month = 3;
        int day = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12: yield 31;
            case 4, 6, 9, 11: yield 30;
            case 2: yield 28;

            default:
                throw new IllegalStateException("존재하지 않는 달입니다.: " + month);
        };
    }

```