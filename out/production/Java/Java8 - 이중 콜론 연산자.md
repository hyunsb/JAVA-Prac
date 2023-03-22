자바에서 이중 콜론(::)은 `메소드 레퍼런스`를 만들기 위해 사용된다. (람다식에서만 사용 가능)

<aside>
✨ 자바 메소드 레퍼런스
메소드를 참조하여 람다식을 더욱 간결하게 만드는 기능입니다. 메소드 레퍼런스를 사용하면 람다식을 더욱 간결하고 가독성있게 표현할 수 있습니다. 이는 자바 8에서 추가된 기능으로, 이중 콜론(::)을 사용하여 메소드를 참조합니다. 이를 통해 불필요한 코드를 줄이고 가독성을 높일 수 있습니다.

</aside>

이중 콜론 연산자는 Java 8 에서 추가된 메소드 참조 연산자이다.
람다식에서 파라미터를 중복해서 사용하고 싶지 않을 때 사용하며, 람다식과 동일한 처리 방법을 갖긴 하지만, 이름으로 기존 메소드를 참조함으로써 더욱 간결하고 보기 좋은 코드를 작성할 수 있다.
(InteliJ에서 자동으로 변환해주지만 한번 짚고가자는 의미에서 공부하도록 하자)

### 사용방법

```java
[인스턴스]::[메소드명(or new)]
User::getId
```

예를 들어, 다음과 같은 코드가 있다고 가정해보자

```java
List<String> names = new ArrayList<>();
names.add("John");
names.add("Jane");

names.forEach(System.out::println);

```

코드에서 `names.forEach(System.out::println)` 부분이 이중 콜론을 사용한 메소드 레퍼런스이다.

이는 `System.out.println()` 메소드를 `forEach()` 메소드에 전달하여 리스트의 모든 요소를 출력하는 코드이다.

또 다른 예로, `Comparator` 인터페이스를 구현할 때 이중 콜론을 사용할 수 있다.

```
List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9);
Collections.sort(numbers, Integer::compare);

```

위 코드에서 `Integer::compare` 부분이 이중 콜론을 사용한 메소드 레퍼런스이며, 이는 `Comparator` 인터페이스의 `compare()` 메소드를 `Integer` 클래스의 `compare()` 메소드로 대체하는 코드이다.