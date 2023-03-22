## 싱글톤 패턴 (Singleton Pattern)

### 자바 싱글톤 패턴이란

자바 싱글톤 패턴(Singleton Pattern)은 객체 생성을 단 한 번만 하고, 그 객체를 어디서든지 참조할 수 있도록 하는 디자인 패턴이다. 이는 메모리 낭비를 줄이고 객체의 일관성을 유지하는 데 도움이 된다.

자바에서 싱글톤 패턴은 다음과 같이 구현한다.

1. 생성자를 `private`으로 선언하여 외부에서 직접 객체를 생성하지 못하도록 한다.
2. 클래스 내부에 자기 자신의 인스턴스를 생성한다.
3. `getInstance()` 메소드를 통해 생성한 인스턴스를 반환한다.

```java
public class Singleton {
    
    // 내부에서 자체적으로 인스턴스 생성
    private static final Singleton instance = new Singleton();

    // 외부에서 인스턴스 생성 불가
    private Singleton(){ } 

    public static Singleton getInstance() {
        return instance;
    }
}
```

위와 같이 구현하면 `Singleton` 클래스의 인스턴스는 클래스가 선언된 첫번 째 실행문에서 단 한 번만 `Heap` 영역에 생성되며, 생성 이후 `getInstance()` 메소드를 통해 어디서든지 참조할 수 있다.

이제 외부 클래스에서 싱글톤 인스턴스가 객체의 일관성을 유지하는지 확인해보자.

```java
Singleton singleton1 = Singleton.getInstance();
Singleton singleton2 = Singleton.getInstance();
// Object singleton2 = Singleton.getInstance(); // 같은 결과

System.out.println( (singleton1 == singleton2) );
```

```java
true
```

결과 적으로 `singleton1` 과 `singleton2` 는 모두 Heap영역에 로딩된 `Singleton` 인스턴스를 가리키게 되며 객체의 일관성을 유지하게 된다.

대표적인 싱글톤 패턴으로는 Java에서의 `Runtime` 클래스와 Spring Framework에서의 `ApplicationContext`가 있다.

Runtime 클래스는 JVM(Java Virtual Machine)의 인스턴스를 나타내며, ApplicationContext는 Spring Framework의 Bean Container 역할을 수행한다.

Runtime 클래스의 인스턴스를 생성하려고 하면 아래와 같이 생성이 불가능 하며

```java
// 'Runtime()' has private access in 'java.lang.Runtime'
Runtime runtime = new Runtime(); 
```

아래와 같이 인스턴스를 참조할 수 있다.

```java
Runtime runtime = Runtime.getRuntime();
```

이 외에도 많은 라이브러리와 프레임워크에서 싱글톤 패턴을 사용하고 있으며, 직접 구현할 수도 있다. 다만, 멀티스레드 환경에서는 동시성문제, 동기화 문제등 여러 문제가 발생할 수 있고, 안전하게 구현하기 위해서는 몇 가지 주의사항이 필요하다.

해당 내용은 추후 다뤄보도록 하겠다.