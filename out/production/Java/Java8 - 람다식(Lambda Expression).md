객체 지향 프로그래밍과 함수적 프로그래밍을 혼합함으로써 더욱 효율적인 프로그래밍이 가능하도록 개발 언어가 변하고 있다. 자바 8에서도 함수적 프로그래밍을 위해 람다식을 지원하게 되었다.

<aside>
✨ 함수적 프로그래밍 (Functional Programming)
함수형 프로그래밍은 거의 모든 것을 순수 함수로 나누어 문제를 해결하는 기법으로, 작은 문제를 해결하기 위한 함수를 작성하여 가독성을 높이고 유지보수를 용이하게 해준다.

</aside>

---

## 람다식?

람다식은 `익명 함수`(anonymous function)을 생성하기 위한 식이다.

객체 지향 언어보다는 함수지향 언어에 가깝다. 자바에서 람다식을 수용한 이유는 자바 코드가 매우 간결해지고, `컬렉션`의 요소를 필터링하거나 매핑해서 원하는 결과를 쉽게 집계할 수 있기 때문이다.

람다식의 형태는 매개변수를 가진 코드 블록이지만, 런타임 시에는 익명 구현 객체를 생성한다.

```
람다식  →  매개 변수를 가진 코드 블록  →  익명 구현 객체
```

예를 들어 `Runnable` 인터페이스의 익명 구현 객체를 생성하는 전형적인 코드는 다음과 같다.

```java
Runnable runnable = new Runnable() {
		public void run() { ... }
}
```

위 코드에서 익명 구현 객체를 람다식으로 표현하면 다음과 같다.

```java
Runnable runnable = () -> { ... };
```

람다식은 마치 함수 정의 형태를 띠고 있지만 런타임 시에 인터페이스의 익명 구현 객체로 생성된다.

---

## 람다식 기본 문법

람다식을 작성하는 방법은 다음과 같다.

```java
( 매개변수 ) -> { 실행문; ... }    // 기본적인 작성 폼

( a ) -> { System.out.prinln(a) } // 예시

a -> { System.out.prinln(a) }     // 하나의 매개변수만 있다면 괄호 생략 가능

a -> System.out.prinln(a)         // 하나의 실행문만 있다면 중괄호 생략 가능

() -> { throw new Exceoption... } // 매개변수가 없다면 빈 괄호를 반드시 사용
```

결과 값을 반환해야 할 시에는 다음과 같이 작성한다.

```java
( x, y ) -> { return x + y; }

실행문에 return 문만 존재할 경우, 다음과 같이 작성한다.

( x, y ) -> x + y;
```

---

## 함수적 인터페이스

```java
[인터페이스] [변수] = [람다식];
```

람다식에 대입될 인터페이스를 람다식의 `타겟 타입`(target type)이라고 한다.

하지만 모든 인터페이스를 람다식의 타겟 타입으로 사용할 수는 없다.

람다식이 하나의 메소드를 정의하기 때문에 두 개 이상의 추상 메소드가 선언된 인터페이스는 람다식을 이용하여 구현 객체를 생성할 수 없다.

하나의 추상 메소드가 선언되어 타겟 타입이 될 수 있는 인터페이스를 함수적 인터페이스(functional interface)라고 한다.

### @FunctionalInterface

`@FunctionalInterface` 어노테이션을 사용하면 함수적 인터페이스를 작성할 때 두 개 이상의 추상 메소드가 선언되지 않도록 컴파일러가 체킹해준다.

```java
@FunctionalInterface
public interface MyFunctionalInterface {

		public void method();
		
		public void otherMethod(); // 컴파일 오류 발생

}
```

`@FunctionalInterface` 어노테이션은 선택사항이며, 해당 어노테이션이 없더라도 하나의 추상 메소드가 있다면 모두 함수적 인터페이스이다.

### 매개변수와 리턴값이 없는 람다식

매개변수와 리턴값이 없는 추상 메소드를 가진 함수적 인터페이스가 있다고 가정한다.

```java
@FunctionalInterface
public interface MyFunctionalInterface {
		public void method();
}
```

`MyFunctionalInterface` 를 타겟 타입으로 갖는 람다식은 다음과 같이 작성과 호출할 수 있다.

```java
MyFunctionalInterface fi = () -> { 실행문; }

fi.method();
```

```java
MyFunctionalInterface fi;

fi = () -> { 
		String str = "method call1";
		System.out.pringln(str); 
};
fi.method();

fi = () -> System.out.println("method call2");
fi.method();
```

### 매개변수가 있는 람다식

두 개의 정수형 매개변수를 받아 최소값을 반환하는 람다식을 작성해보자

```java
@FunctionalInterface
interface MyFunctionalInterface {
    int min(int a, int b);
}
```

```java
public class FunctionalInterfaceTest {
    public static void main(String[] args) {

        MyFunctionalInterface myFunction1 = (x, y) -> x < y ? x : y;
        MyFunctionalInterface myFunction2 = (x, y) -> Math.min(x, y);
        MyFunctionalInterface myFunction3 = Math::min;  // !이중 콜론 연산자

        System.out.println(myFunction1.min(99, 100));
        System.out.println(myFunction2.min(99, 100));
        System.out.println(myFunction3.min(99, 100));

    }
}
```

❗람다식에서는 이중 콜론 연산자로 동일한 로직을 실행하는 코드를 더 간결하게 작성할 수 있다.

[Java8 - 이중 콜론 연산자 (Double Colon Operater)](https://www.notion.so/Java8-Double-Colon-Operater-34b49e371e6240869bd6ecee7d95c4e0)

---

## 클래스 멤버와 로컬 변수 사용

람다식 실행 블록에서는 클래스의 멤버인 필드와 메소드를 제약 사항 없이 사용할 수 있다.

하지만 this 키워드를 사용할 때 주의가 필요하다.

익명 객체 내부에서의 this는 익명 객체의 참조이지만, 람다식에서 this는 람다식을 사용한 객체의 참조이다.

아래의 코드처럼 인터페이스와 인터페이스의 추상메서드를 람다식으로 구현한 클래스가 존재한다고 예를들자

```java
@FunctionalInterface
public interface MyFunctionalInterface {
		public void method();
}
```

```java
class ThisKeyWord {
    public String field = "Outer Class Field";

    class Inner {
        String field = "Inner Class Field";

        void method() {
            MyFunctionalInterface fi = () -> {
                String field = "lambda field";

                System.out.println(field);
                System.out.println(this.field);
                System.out.println(ThisKeyWord.this.field);
            };
            fi.method();
        }

    }
}
```

inner 클래스의 인터페이스를 생성하고 람다식으로 작성된 메서드를 실행시켜 보면
어떤 field 변수를 참조하여 출력할까

```java
public class ThisKeyWordTest {

    String field = "main class field";

    public static void main(String[] args) {

        String field = "main method field";

        ThisKeyWord thisKeyWord = new ThisKeyWord();
        ThisKeyWord.Inner inner = thisKeyWord.new Inner();

        inner.method();
    }
}
```

```java
lambda field
Inner Class Field
Outer Class Field
```

람다식을 사용한 객체의 변수를 참조하는 것을 확인할 수 있다.

---

## 표준 API의 함수적 인터페이스

자바에서 제공되는 표준 `API`에서 한 개의 추상 메소드를 가지는 인터페이스들은 모두 람다식을 이용하여 익명 구현 객체로 표현이 가능하다.

예를 들어 스레드의 작업을 정의하는 `Runnable` 인터페이스는 매개변수와 리턴값이 없는 `run()` 메소드만 정의하고 있기 때문에 람다식을 사용하여 Runnable 인터페이스를 생성할 수 있다.

```java
Runnable runnable = () -> {
		for(int i = 0; i < 10; i++) System.out.prinln(i);
}

Thread thread = new Thread(runnabe);
thread.start();
```

이는 Thread 생성자를 호출할 때 다음과 같이 람다식을 매개값으로 사용할 수도 있다.

```java
Thread thread = new Thread( () -> {
				for(int i = 0; i < 10; i++) System.out.println(i);
		}
);
```

람다식을 사용하지 않으면 다음과 같이 메소드를 오버라이드하여 익명 함수를 작성하여야 한다.

```java
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        for(int i=0; i<10; i++)
            System.out.println(i);
    }
};

Thread thread = new Thread(runnable);
thread.start();
```

람다식을 사용함으로써 익명 함수의 정의를 간결하고 가독성좋게 작성할 수 있다.

Java 8 부터 빈번하게 사용되는 함수적 인터페이스(functional interface)는 `java.util.function` 표준 API 패키지로 제공한다.

| 종류 | 매개값 | 리턴값 | 특징 |
| --- | --- | --- | --- |
| Consumer | 🟢 | ❌ |  |
| Supplier | ❌ | 🟢 |  |
| Function | 🟢 | 🟢 | 주로 매개값을 리턴값으로 매핑(타입 변환) |
| Operator | 🟢 | 🟢 | 주로 매개값을 연산하고 결과를 반환 |
| Predicate | 🟢 | 🟢 (boolean) | 매개값을 조사하여 true/false 반환 |

---

## 오류

`Target type of a lambda conversion must be an interface`

- 컴파일러가 람다식이 어떤 함수형 인터페이스를 구현하고 있는지 알 수 없는 것이다.

    ```java
    Object fi = () -> System.out.println("lambda test");
    
    // Target type of a lambda conversion must be an interface 발생
    ```

  해당 오류는 아래와 같이 인터페이스를 명시해주어 해결할 수 있다.

    ```java
    Object fi = (Runnable)() -> System.out.println("lambda test");
    ```


---

## 결론

- 람다식은 `익명함수`를 간결하게 작성할 수 있음
- `[인터페이스] [변수] = [람다식]` 형식으로 작성
- 람다식에 대입될 인터페이스를 `타겟 타입`이라 함
- `이중 콜론 연산자` 사용가능
- 람다식의 `this` 키워드는 람다식을 구현한 클래스를 참조함