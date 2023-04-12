## 제네릭이란?

> generic : 일반적인
>

클래스에서 사용하는 변수의 자료형이 여러개 이며, 메소드는 동일한 경우 클래스의 자료형을 특정하지 않고 추후 해당 클래스를 사용할 때 지정 할 수 있도록 선언한다.

컬렉션 프레임워크에서 많이 사용되고 있다.

제네릭은 타입에러의 발생을 방지한다.

런타임에서 타입 에러가 발생하는 것보다 컴파일 시에 미리 타입을 체크하여 에러를 사전에 방지하는 것이 좋다.

그렇다면 왜 굳이 제네릭을 사용해야 할까?

클래스의 멤버를 Object로 선언하여 모든 타입을 담을 수 있게 만들면 되지 않는가?

Object는 문제점이 하나 존재한다. 필요에 의해 타입을 변환해줘야 한다는 점이다.

제네릭은 타입 변환을 제거한다.

코드에서 불필요한 타입 변환을 제거하여 프로그램 성능을 향샹 시킨다.

만약 리스트에 문자열 요소를 저장하고 문자열을 찾아와 변수에 저장하려고 한다.

제네릭이 없는 경우 문자열 요소는 `list`에 `Object`로 저장되기 때문에 타입 캐스팅이 필수 된다.

```java
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0);
```

하지만 제네릭을 사용할 경우 타입 캐스팅이 필요가 없어지게 된다.

```java
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0);
```

---

## 제네릭 타입 선언하기

제네릭 타입은 타입을 파라미터로 가지는 클래스와 인터페이스를 의미한다.

클래스 또는 인터페이스 이름 뒤에 `<>`부호가 붙고, 사이에 타입 파라미터가 위치한다.

```java
public class 클래스명<T> { ... }
public interface 인터페이스명<T> { ... }
```

`<>`부호 안에는 여러 알파벳이 올 수 있다. `E`: element, `K`: key, `V`: value …

클래스나 인터페이스에서 사용하는 변수의 의미에 맞게 본인이 임의로 알파벳을 지정하면 된다.

아래와 같이 두 개 이상의 멀티 타입 파라미터를 사용할 수 있다.

```java
public class 클래스명<K, V> { ... }
public interface 인터페이스명<K, V> { ... }
```

## 제네릭 메소드 선언하기

제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 가지는 메소드를 의미한다.

제네릭 타입 `Box`가 존재한다.

```java
public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
```

제네릭 메소드를 가지는 `Util`클래스를 선언한다.

```java
public class Util {
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<>();
        box.set(t);
        return box;
    }
}
```

제네릭 메소드를 선언하는 방법은 아래와 같다.

```java
public <타입 파라미터, ... > 리턴타입 메소드명(매개변수, ... ) { ... }
```

다음으로 제네릭 메소드를 호출하는 방법이다.

```java
Box<Integer> IntegerBox = Util.<Integer>boxing(100); // 명시적으로 타입 지정
Box<String> stringBox = Util.boxing("String"); // 매개값으로 컴파일러가 타입 추정
```

## 타입 파라미터 제한하기

타입 파라미터의 타입을 제한할 수 있다.

아래의 코드는 타입 파라미터의 타입을 `Number`타입 또는 하위 클래스 타입으로 제한한다.

```java
public static <T extends Number> int compare(T t1, T t2){
    double v1 = t1.doubleValue();
    double v2 = t2.doubleValue();
    return Double.compare(v1, v2);
}
```

## 와일드카드, 상위, 하위 타입

코드에서 `?`를 일반적으로 `와일드카드`(wildcard)라고 부른다.

```java
public class<?> {...}  // 와일드카드, 모든 클래스나 인터페이스가 올 수 있음

public class<? extends 타입> // 타입을 포함한 하위타입들만 올 수 있음

public class<? super 타입> // 타입을 포함한 상위타입들만 올 수 있음
```