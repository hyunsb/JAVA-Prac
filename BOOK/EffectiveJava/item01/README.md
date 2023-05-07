## 학습내용

- 객체를 만들어야 할 때와 만들지 말아야 할 때를 구분하는 법
- 올바른 객체 생성 방법과 불필요한 생성을 피하는 법
- 제때 파괴됨을 보장하고 파괴 전에 수행해야 할 정리 작업을 관리하는 요령

## 생성자 대신 정적 팩토리 메서드를 고려하라

클래스는 생성자와 별도로 정적 팩토리 메서드(static factory method)를 제공할 수 있다.

정적 팩터리 메서드는 해당 클래스의 인스턴스를 반환한다.

즉, 어떤 클래스를 인스턴스화할 때, 생성자를 직접 호출하는 것이 아닌 내부적으로 생성자를 호출하거나 인스턴스를 반환하는 어떤 메서드를 호출하여 인스턴스를 생성하는 방법이다.

## 정적 팩토리 메서드의 장점

1. 이름을 가질 수 있다.
2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
5. 정적 팩토리 메서드를 작성하는 시점에는 반환할 클래스가 존재하지 않아도 된다.

### 이름을 가질 수 있다.

커피와 디카페인커피를 생성할 수 있다고 가정하자.

```java
Coffee latte = new Coffee("latte", 10)
Coffee decafLatte = new Coffee("decaflatte");
```

위의 코드에서는 생성자의 의도를 변수를 통해서 확인할 수 있다.

하지만 변수 없이 생성하는 경우에는? 생성자의 의도를 파악하기 힘들 것이다.

```java
customer.eat(new Coffee("latte", 10));
```

이를 정적 팩토리 메서드를 사용하여 조금 더 직관적으로 인스턴스를 생성할 수 있다.

```java
Coffee latte = Coffee.makeCoffee("latte", 10);
Coffee decafLatte = Coffee.makeDecafCoffee("latte");
```

이제 우리는 메서드를 통해서도 어떤 커피를 생성하는지 의도를 파악할 수 있다.

### 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.

대표적으로  enum을 예로 들 수 있겠다.

enum혹은 불변 클래스는 미리 인스턴스를 만들어놓고 해당 인스턴스를 조회(캐싱)하여 재활용하는 식으로 불필요한 객체 생성을 피한다.

```java
public class Day {
		private static final Map<String, Day> days = new HashMap<>();

		static {
				days.put("mon", new Day("Monday"));
				days.put("tue", new Day("Tuesday"));
				days.put("wen", new Day("Wednesday"));
				days.put("thu", new Day("Thursday"));
				days.put("fri", new Day("Friday"));
				days.put("sat", new Day("Saturday"));
				days.put("sun", new Day("Sunday"));
		}

		public static Day from(String day) {
				return days.get(day);
		}

		private Day(String day) {
				this.day = day;
		}

		public String getDay() {
				return day;
		}
}
```

이제 요일을 사용할 때마다 매번 새로운 Day객체를 생성하는 것이 아닌 미리 생성된 Day를 캐싱하는 식으로 사용할 수 있다.

```java
Day day = Day.from("mon");
```

플라이웨이트 패턴도 이와 비슷한 기법이라 할 수 있다.

[[디자인 패턴] Flyweight 패턴](https://velog.io/@hoit_98/디자인-패턴-Flyweight-패턴)

반복되는 요청에 같은 객체를 반환하는 방식으로 클래스는 언제 어느 인스턴스를 살아 있게 할지를 통제할 수 있다.

이런 클래스를 인스턴스 통제 클래스라 한다.

왜 인스턴스를 통제하는 것일까?

인스턴스를 통제하면 클래스를 싱글턴, 인스턴스화 불가, 불변 값 클래스에서 동치인 인스턴스가 단 하나뿐임을 보장할 수 있다.

### 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.

### 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.

### 정적 팩토리 메서드를 작성하는 시점에는 반환할 클래스가 존재하지 않아도 된다.