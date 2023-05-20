# Item16 - public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

## 핵심 내용

- public 클래스는 절대 가변 필드를 직접 노출해서는 안된다.
- 불변 필드라면 위험도는 낮아지지만 완전히 안심할 수는 없다.
- package-private 혹은 private 내부 클래스는 필드를 노출하는 편이 나을 때도 있다.

이 책에서 말하는 데이터 표현 방식이 의미하는 바를 이해하지 못했다.

## public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

```java
public class Point {
		public int x;
		public int y;
}
```

정말 아무런 의미가 없는 클래스이다. 캡슐화의 이점을 하나도 얻지 못한다.

보통 우리는 멤버를 private으로 변경하고 getter, setter를 통해 필드에 접근할 것이다.

이 때 getter, setter를 접근자라 칭한다.

> 패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공함으로써 클래스 내부 표현 방식을 언제든 바꿀 수 있는 유연성을 얻을 수 있다.
>

위의 문장을 처음 접했을 때는 무슨 말인지 이해를 하지 못했다.

이후에 private필드를 getter, setter로 접근하는 게 정말 정보 은닉인가?에 대해 고민을 하게 되었는데 위의 문장이 떠올랐다.

```java
public class Point {
		public int a;
}
```

다른 패키지에서 위의 Point 클래스를 사용하고 있다고 가정하자.

개발자인 나는 Point의 멤버변수인 a의 타입을 String으로 변경하고 싶다.

클래스의 필드가 위와 같이 공개되어있다면 멤버를 String으로 변경했을 때, 이를 사용하고 있는 여러 프로그램에서 오류가 발생할 것이다. 하지만 Point클래스에서는 아무런 오류가 발생하지 않을 것이다.

이는 정보은닉의 이점을 하나도 얻지 못한다고 볼 수 있다.

하지만, 아래처럼 접근자를 사용한다면?

```java
public class Point{
		private int a;
		
		public int getA() {
				return a;
		}
}
```

접근자를 사용했을 때, 우리가 필드의 타입을 변경한다면? 이를 사용하고 있는 프로그램들이 오류를 던지기 전에 해당 클래스에서 먼저 컴파일 오류가 발생할 것이다.

```java
public class Point{
		private String a;
		
		// 컴파일 오류 발생!!
		public int getA(){
				return a;
		}
}
```

그렇긴 때문에 오류의 발생 근원을 쉽게 파악할 수 있고, 이를 통해 API에서 발생하게 되는 컴파일 오류의 수정만 제대로 한다면 이를 사용하고 있는 프로그램들에게는 아무런 문제가 발생하지 않는다.

```java
public class Point{
		private String a;

		public int getA(){
				return Integer.parseInt(a);
		}
}
```

하지만 Point 클래스가 package-private 클래스 혹은 private 중첩 클래스라면?

```java
class Point {
		public int x;
		public int y;
}

class OuterClass{
		private static class Point {
				public int x;
				public int y;
		}
}
```

데이터 필드를 public 으로 노출한다 하더라도 아무런 문제가 없다.

해당 클래스는 어떠한 클래스의 내부 혹은 패키지안에서만 동작하기 때문이다.