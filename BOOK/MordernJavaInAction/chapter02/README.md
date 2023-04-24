# Chapter 02. 동작 파라미터화 코드 전달하기

동작 파라미터화를 사용하면 자주 변경되는 요구사항에 효과적으로 대응할 수 있다.

동작 파라미터화 (behavior parameterization)?

동작 파라미터화란 아직은 어떠ㅎ게 실행할 것인지 결정하지 않은 코드를 블록을 의미한다.

동작 파라미터화를 추가하려면 쓸데없는 코드가 늘어나는데, 자바8은 람다로 이 문제를 해결한다.

## 변화하는 요구사항에 대응하기

변화에 대응하는 코드를 구현하는 것은 어려운 일이다.

농장의 재고목록 애플리케이션에 리스트에서 녹색 사과만 필터링하는 기능을 추가한다고 가정한다.

### 첫 번째 시도: 녹색 사과 필터링

```java
enum { RED, GREEN }

public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) 
				if (Green.equals(apple.getColor())) // 녹색 사과마 선택
							result.add(apple);

		return result;
} 
```

그런데 갑자기 농부가 빨간 사과도 필터링하고 싶어졌다면, 어떻게 리팩터링 해야할까?

메서드를 복사해서 `filterRedApples`라는 새로운 메서드를 만들어야 할까?

가능은 하겠지만 해당 방법은 코드가 중복되고, 변화에는 유연하게 대응할 수 없다.

농부가 옅은 녹색, 어두운 빨간색, 노란색 등의 사과도 필터링 하고 싶어 졌다면?? 같은 방법을 사용할 것인가?

### 두 번째 시도: 색을 파라미터 화

위의 방식보다 조금 더 변화에 유연하게 대응가능한 코드를 작성해보겠다.

```java
public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory)
				if (apple.getColor().equals(color))
						result.add(apple);

		return result;
}
```

이제 농부가 원하는 모든 색의 사과를 필터링할 수 있게 되었다.

```java
List<Apple> greenApples = filterApplesByColor(inventory, GREEN);
List<Apple> redApples = filterApplesByColor(inventory, RED);
```

이번엔 농부가 색 이외에 다른 분류 기준을 내놓았다.

무게가 가벼운(150 그램 미만)과 무거운 사과를 분류하고 싶어한다.

```java
public static List<Apple> filterApplesByColor(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory)
				if (apple.getWeight() > weight)
						result.add(apple);

		return result;
}
```

무게라는 파라미터를 추가하여 해당 문제를 해결할 수 있을 것이다.

하지만 해당 코드는 색을 기준으로 필터링하는 코드와 매우 유사하며 코드의 대부분이 중복된다는 것을 확인할 수 있다.

이는 소프트웨어 공학의 `DRY`(Don’t repeat yourself; 같은 것을 반복하지 말 것) 원칙을 어기는 것이다.

## 동작 파라미터화

우리는 위의 과정을 거치며 메소드나 파라미터를 추가하는 것이 아닌 변화하는 요구사항에 조금 더 유연하게 대응할 수 있는 방법이 절실하다는 것을 확인했다.

이를 위해 참 또는 거짓을 반환하는 프레디케이트 인터페이스를 작성한다.

```java
public interface ApplePredicate {
		boolean test (Apple apple);
}
```

프레디케이트 인터페이스를 구현하여 여러버전의 ApplePredicate를 정의할 수 있다.

```java
public class AppleHeavyWeightPredicate implements ApplePredicate {
		public boolean test (Apple apple) {
				return apple.getWeight() > 150;
		}
}

public class AppleGreenColorPredicate implements ApplePredicate {
		public boolean test (Apple apple) {
				return Green.equals(apple.getColor());
		}
}
```

이제 위의 조건에 따라 filter 메서드가 다르게 동작할 것이라고 예상할 수 있으며

이를 전략 디자인 패턴(strategy design pattern)이라고 부른다.

자, 이제 프레디케이트를 통해 filter메서드가 어떻게 `동작`할지 `파라미터`로 전달할 수 있다.

### 세 번째 시도: 추상적 조건으로 필터링

앞에서 선언한 predicate로 동작을 파라미터로 전달해보자.

```java
public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory)
				if (predicate.test(apple)) result.add(apple);
		
		return result;
}
```

위와 같이 filter메서드를 선언했다면 이제 동작을 넘겨주기만 하면 된다.

```java
List<apple> redAndHeavyApples = 
									filterApples(inventory, new AppleRedAndHeavyPredicate);
```

이처럼 컬렉션의 탐색 로직과 각 항목에 적용할 동작을 분리할 수 있다는 것이 동작 파라미터화의 강점이다.

동작을 인자로 넘겨줌으로써 한 메서드가 여러 다른 동작을 수행할 수 있도록 재활용할 수 있다. 따라서 유연한 API를 만들 때 동작 파라미터화가 중요한 역할을 한다.

## 복잡한 과정 간소화

위의 과정으로 변화하는 요구사항에 대해 유연한 대처가 가능해졌다.

하지만 아직 불편한 점은 존재한다. 만약 사과의 분류 기준이 엄청 많아진다면 어떡할 것인가? 100가지라면? 100가지에 대한 모든 분류 기준을 Predicate를 정의하고 인스턴스화 해야할까?

이는 상당한 번거로운 작업이며 시간 낭비이다.

해당 문제를 해결하기 위해 자바는 익명 클래스 라는 기법을 제공한다.

### 네 번째 시도: 익명 클래스 사용

익명 클래스를 사용하여 위의 문제를 해결해보자.

```java
List<apple> redApples = filterApples(inventory, new ApplePredicate() {
		public boolean test(Apple apple) {
				return RED.equals(apple.getColor());		
		}
})
```

위의 코드보다 더욱 동작 파라미터화 라는 말에 어울리는 코드가 되었다.

### 다섯 번째 시도: 람다 표현식 사용

람다 표현식을 사용하면 익명 클래스를 사용한 코드를 더욱 간결하게 표현할 수 있다.

```java
List<Apple> result =
			filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
```

### 여섯 번째 시도: 리스트 형식으로 추상화

```java
public interface Predicate<T> {
		boolean test(T t);
}

public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list)
				if (p.test(e)) result.add(e);
		return result;
}
```

이제 필터를 사과에 국한되지 않고 바나나, 오렌지 심지어 정수, 문자열등의 리스트에 필터 메서드를 사용할 수 있다.

```java
List<Apple> redApples = 
			filter(inventory, apple -> RED.equals(apple.getColor()));

List<Integer> evenNumbers = 
			filter(numbers, i -> i % 2 == 0);
```

## 실전예제

코드 전달의 개념을 더욱 확실하게 익힐 수 있도록 Comparator로 정렬하기, Runnable로 코드 블록 실행하기, Callable을 결과로 반환하기, GUI 이벤트 처리하기등을 학습한다.

아래에서 설명할 인터페이스들은 추상 메서드가 한개만 존재하는데 이를 함수형 인터페이스(Functional Interface)라고 부른다.

### Comparator로 정렬하기

`java.util` 패키지에 아래와 같은 `Comparator` 인터페이스가 선언되어 있다.

```java
// java.util.Comparator
public interface Comparator<T> {
		int compare(T o1, T o2);
}
```

Comparator를 구현해서 sort 메서드의 동작을 다양화 할 수 있다.

```java
inventory.sort(new Comparator<Apple>() {
		public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight()); // 기본적으로 오름차순
		}
});
```

이를 람다 표현식으로 변경하면

```java
inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
```

### Runnable로 코드 블록 실행하기

Runnable은 스레드를 이용한 병렬 처리 방법에서 자주 사용된다.

```java
// java.lang.Runnable
public interface Runnable {
		void run();
}
```

```java
Thread thread = new Thread(new Runnable(){
		public void run() {
				System.out.println("Hello, World");
		}
});
```

이를 람다 표현식으로 변경하면

```java
Thread thread = new Thread( () -> System.out.println("Hello, World"));
```

### Callable을 결과로 반환하기

해당 방식은 나중에 자세하게 다룰 것이기 때문에 Runnable의 업그레이드 버전이라고 생각하고 넘어가자

```java
// java.util.Callable
public interface Callable<V> {
		V call();
}
```

테스크를 실행하는 스레드의 이름을 반환하는 예제이다.

```java
ExecuteService excutorService = Excutors.newCachedThreadPool();
Future<String> threadName = excutorService.submit(new Callable<String>() {
		@Override
		public String call throws Exception {
				return Thread.currentThread().getName();
		}
});
```

```java
Future<String> threadName = excutorService.submit( () -> {
		Thread.currentThread().getName;
});
```

### GUI 이벤트 처리하기

```java
Button button = new Button("Send");
button.setOnAction(new EventHandlr<ActionEvent>() {
		public void handle(ActionEvent event) {
				label.setText("Sent!!");
		}
});
```

EventHandler는 setOnAction 메서드의 동작을 파라미터화한다.

```java
button.setOnAction(event -> label.setText("Sent!"));
```

## 정리

- 동작 파라미터화에서는 메서드 내부적으로 다양한 동작을 수행할 수 있도록 코드를 메서드의 인수로 전달한다.
- 동작 파라미터화를 이용하면 변화하는 요구사항에 유연하게 대응할 수 있는 코드를 구현할 수 있다.
- 코드 전달 기법을 이용하여 동작을 메서드의 인수로 전달할 수 있다. 익명클래스 혹은 람다표현식을 사용하면 깔끔한 코드를 작성할 수 있다.