IPC 기법으로 서로 통신하는 멀티 프로세스와 달리 멀티 스레드는 메모리를 공유한다.

메모리를 공유하기 때문에 하나의 스레드가 예외를 발생시키면 프로세스 자체가 종료될 수 있어 다른 스레드에게 영향을 미치게 된다. 그렇기에 멀티 스레드에서는 예외 처리에 만전을 기해야 한다.

모든 자바 애플리케이션은 `메인 스레드`가(main thread) `main()` 메소드를 실행하면서 시작한다.

메인 스레드는 메인 메소드의 코드를 순차적으로 수행한다.

```java
public static void main(String[] args) {
		String data = null;

		if(...) {
		}

		while(...) {
		}
}
```

메인 스레드는 작업 스레드들을 만들어서 병렬로 코드를 실행할 수 있다.

## 작업 스레드 생성과 실행

멀티 스레드로 실행하는 애플리케이션을 갭라하려면 메인 스레드 이외의 추가적인 병렬 작업 수 만큼 스레드를 생성하면 된다.

아래와 같이 `Runnable`을 인자로 가지는 `Thread` 생성자를 호출하여 스레드를 생성할 수 있다.

```java
Thread thread = new Thread(Runnable target);
```

Runnable의 `run()` 메서드를 구현하는 클래스를 생성하고, 이것을 인자로 `Thread`생성자를 호출하면 작업 스레드가 생성되는 것이다.

```java
public class Task implements Runnable{
    @Override
    public void run() {
        // 스레드가 실행할 코드
    }
}
```

```java
Runnable task = new Task();
Thread thread = new Thread(task);
```

아래와 같이 익명 객체를 매개값으로 넘기는 경우가 더 많이 사용된다.

또한, `Runnable` 인터페이스는 메소드가 하나만 존재하는 함수적 인터페이스이다.

즉, 람다로 표현할 수 있다.

```java
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
				// 스레드가 실행할 코드
    }
});
```

```java
Thread thread = new Thread(() -> {
   // 스레드가 실행할 코드
});
```

`thread.start();` 로 스레드를 실행할 수 있다.

아래와 같이 멀티스레드로 소리와, 텍스트를 동시에 출력할 수 있다.

```java
Thread thread1 = new Thread(() -> {
    Toolkit toolkit =Toolkit.getDefaultToolkit();
    for (int i = 0; i < 5; i++) {
        toolkit.beep();
        try { Thread.sleep(500); } catch (Exception ignored) {}
    }
});
thread1.start();

Thread thread2 = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("띵");
        try { Thread.sleep(500); } catch (Exception ignored) {}
    }
});
thread2.start();
```

---

## 스레드 우선순위

`동시성`: 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아 가며 실행하는 성질

`병렬성`: 멀티 작업을 위해 코어에서 개별 스레드들을 동시에 실행하는 성질

스레드의 개수가 코어의 수 보다 많을 경우, 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 결정해야 한다. 이것을 스레드 스케줄링이라고 한다.

스레드 스케줄링에 의해 스레드들은 아주 짧은 시간에 번갈아가면서 그들의 `run()`메소드를 조금씩 실행한다.

자바의 스레드 스케줄링은 우선순위(`Priority`) 방식과 순환 할당(`Round-Robin`) 방식을 사용한다.

- 우선순위: 우선순위가 높은 스레드가 실행 상태를 더 많이 가짐

    ```java
    // 스레드의 우선순위 설정 방법
    thread.setPriority(우선순위) // 우선순위 1~10
    ```

- 순환 할당: 시간 할당량(Time-slice)를 정해서 각 스레드를 정해진 시간만큼 실행하는 방식

---

## 동기화 메소드와 동기화 블록

멀티 스레드 프로그램에서는 스레드들이 객체를 공유해서 작업해야 하는 경우가 있다.

메모리에 인자값을 저장하고, 스레드를 2초간 일시 정지한 뒤 메모리를 출력하는 계산기가 있다.

```java
public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {}
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}
```

그리고 각각 스레드를 생성하여 계산기의 메모리에 `100`, `50`을 저장하는 `User1, 2` 객체가 있다.

```java
public class User1 extends Thread{
    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User1");
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory(100);
    }
}
```

```java
public class User2 extends Thread{
    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User2");
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory(50);
    }
}
```

`User1`은 50을, `User2`는 100을 계산기의 메모리에 저장하였다.

```java
public static void main(String[] args) {
    Calculator calculator = new Calculator();

    User1 user1 = new User1();
    user1.setCalculator(calculator);
    user1.start();

    User2 user2 = new User2();
    user2.setCalculator(calculator);
    user2.start();
}
```

```java
User1: 50
User2: 50
```

`User1`의 스레드가 메모리에 100을 저장하고 2초간 일시정지 한 사이에 `User2` 스레드가 메모리의 값을 50으로 바꾸어 버렸기 때문에 두 객체 모두 50을 출력하게 된다.

스레드가 사용중인 객체를 다른 스레드가 변경할 수 없도록 하려면 `임계영역`(critical section)을 지정하야 한다.

`임계영역`이란 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역이란 뜻이다.

자바에서는 이러한 `critical section`을 `synchronized`(동기화) 메소드와 블록을 통해 지정할 수 있다.

`Calculator` 객체의 메서드에 아래와 같이 `sychronized` 키워드를 붙여준다. (동기화 메소드 방법)

```java
public synchronized void setMemory(int memory) {
    this.memory = memory;
    try {
        Thread.sleep(2000);
    } catch (InterruptedException ignore) {}
    System.out.println(Thread.currentThread().getName() + ": " + this.memory);
}
```

```java
User1: 100
User2: 50
```

해결 완료.

다음은 동기화 블럭을 이용하여 임계영역으로 구분해보겠다.

```java
public void setMemory(int memory) {
    synchronized (this){ // this: 공유 객체인 Calculator의 참조
        this.memory = memory;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {}
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}
```

해결 완료.

스레드가 동기화 블록으로 들어가면 `this` 를 잠그고, 동기화 블록을 실행한다.

동기화 블록을 모두 실행할 때까지 다른 스레드들은 this의 모든 동기화 메소드 또는 동기화 블록을 실행할 수 없게 된다.