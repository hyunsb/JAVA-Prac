## 단락 회로 평가 (short Circuit Evaluation)

`단락 회로 평가(short Circuit Evaluation)`란, 논리 연산에서 전체 식을 평가하지 않고, 일부 조건식만으로 전체 식의 값을 결정하는 방법을 말한다. 예를 들어, `&&` 연산자에서 왼쪽 조건식이 `false`일 경우, 전체 식은 `false`가 되기 때문에 오른쪽 조건식을 평가하지 않는다. 이를 통해, 시간과 자원을 절약할 수 있다.

```java
public class ShortCircuit {
    public static void main(String[] args) {
        int num1 = 10, num2 = 20;

        if (num1 < 5 && num2++ > 30) {
            System.out.println("This will not print");
        } else {
            System.out.println("num1 or num2 is greater than or equal to 5");
            System.out.println("num2 = " + num2);
        }
    }
}

```

위 코드에서 `if`문 안의 조건식에서 `num1`이 5보다 작은 조건은 참이므로, 다음 조건인 `num2++ > 30`은 실행되지 않는다. 따라서 `num2`의 값은 변경되지 않아 20이 출력된다.