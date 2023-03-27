## 바인딩 (binding)

바인딩이란 컴퓨터 프로그램에서 각종 값들이 더 이상 변경되지 않는 값으로 구속 되는 것입니다.

풀어서 설명해보자면, **변수**(식별자, identifier)가 각종 타입에 의해 **데이터형이 확정**되는것, **변수**가 메모리 주소를 가리키거나 **값을 가지는 것**을 바인딩이라고 합니다.

바인딩은 컴파일 시에도 수행되고, 런타임 시에도 수행됩니다. 컴파일 과정에서 수행되는 바인딩을 정적(static) 바인딩이라고 하며, 런타임 과정에서 수행되는 바인딩을 동적(dynamic) 바인딩이라고 합니다.  정확하게는 컴파일 혹은 런타임에서 함수의 호출이 결정된다 라고 표현하는 게 맞는 것 같습니다.

---

## 정적 바인딩 (Static Binding)

컴파일 시에 결정되는 바인딩을 정적 바인딩이라고 합니다.

메소드 오버로딩(overloading)가 대표적인 예인데요.

```java
public class OverLoading {
		public void print() {
				System.out.println("매개변수 없는 print");
		}
			
		public void print(String param) {
				System.out.println("매개변수가 있는 print");
		}

		public void print(String param1, String param2){
				System.out.println("매개변수가 2개 있는 print");
		}
}
```

위와 같은 클래스가 있다고 가정해봅시다. OverLoading 클래스는 매개변수를 가지지 않는 print() 메소드와 String 타입 매개변수를 하나 전달받는 오버로드된 print() 메소드가 있습니다.

```java
OverLoading ol = new OverLoading();

ol.print();
ol.print("매개변수");
ol.print("매개변수1", "매개변수2");
```

자 여기서 OverLoading 클래스의 어떤 print() 메소드를 호출할 지는 컴파일러에 의해 결정되게 됩니다. 오버로딩된 메소드는 매개변수의 유무, 종류, 개수가 다르기 때문에 컴파일 과정에서 이를 구분할 수 있기 때문이죠.

다른 예시를 들어 보겠습니다. `School` 클래스와 이를 is-a 상속 받는 `Classroom` 클래스가 있습니다.

두 클래스 모두 `ringBell()` 메소드를 `static`으로 정의합니다. (static method는 상속 불가능)

```java
class School{
    public static void ringBell(){
        System.out.println("Ringing the school bell...");
    }
}

class Classroom extends School {
    public static void ringBell() {
        System.out.println("Ringing the classroom bell...");
    }
}
```

메인 함수에서 아래와 같이 인스턴스를 생성하고 메소드를 호출하면 어떻게 될까요?

```java
School school1 = new School();
school1.ringBell(); // 정적 바인딩이기 때문에 School.ringBell()을 자동으로 호출
School1.ringBell();

Classroom classroom1 = new Classroom();
school1.ringBell(); // 정적 바인딩이기 때문에 Classroom.ringBell()을 자동으로 호출
School1.ringBell();

School shcool2 = new Classroom();
school2.ringBell(); // 정적 바인딩이기 때문에 School 클래스의 ringBell()이 호출
```

`static` 멤버는 컴파일 시 `Class` 와 함께 `JVM`의 `Method Area`에 로드되는 것이 결정됩니다.

`static` 키워드가 붙은 멤버들은 인스턴스에 소속된 멤버가 아닌 클래스에 소속된 멤버이기 때문에 클래스 변수 혹은 클래스 메소드라고 부릅니다.

`new` 를 통해 객체의 여러 인스턴스를 생성하더라도 생성된 인스턴스는 서로 독립적이지만, `static` 키워드가 붙은 멤버들은 모든 인스턴스가 같은 메모리 영역을 공유하기 때문에 모두 같은 멤버가 호출됩니다.

결국 `School` 타입 변수에 `Classroom` 의 생성자를 호출하여 저장하더라도 컴파일러에 의해 논리적으로 `school2` 변수는 `School` 타입이니까 `School` 의 메소드를 실행시켜야겠구나! 라고 결정 되는 것입니다.

하지만 매개변수의 유무, 종류, 개수로 메소드를 판단할 수 없는 오버라이딩(overriding)은 어떨까요?

---

## 동적 바인딩 (Dynamic Binding)

런타임 시 수행되는 바인딩을 동적 바인딩이라고 합니다.

오버라이딩이 동적 바인딩의 대표적인 예 입니다.

아래의 `Exam` 클래스와 `FinalExam`클래스를 예로 들면 볼게요.

```java
class Exam {
		public void total(){
				return kor + eng + math;
		}
}

class FinalExam extends Exam{
		@Override
		public void total(){
				return super.total() + com;
		}
}
```

`FinalExam` 클래스는 `Exam` 클래스를 `is-a` 방식으로 상속받고, `total()` 메소드를 오버라이드하여 기능을 조금 추가로 구현했습니다.

이제 임의의 클래스에서 아래의 메소드를 호출하게 된다면? 어떤 객체의 메소드가 호출되게 될까요?

```java
public void print(Exam exam) {
		System.out.println( exam.total() );
}
```

파라매터로 전달된 객체에 따라 호출되는 메소드가 다르게 되겠죠. 파라미터로 전달되는 인스턴스가 `Exam`일 수도 `FinalExam`일 수도 있기 때문입니다. 위의 메소드를 가지는 클래스는 자신이 어떤 인스턴스를 전달받을 지 모릅니다. 프로그램이 실행되고 위의 메소드가 호출이되면 그 때 알 수 있습니다.

이것을 런타임 시 호출될 메소드가 결정되는 것, 동적 바인딩이라고 합니다.

위에서 예시로 들었던 `School` 과 `Classroom` 객체로 다른 예를 들어 보겠습니다.

`static`으로 선언되어 있던 `ringBell()` 메소드에 `static`을 제거하고 `override` 하는 형식으로 변경 시켜주었습니다.

```java
class School{
    public void ringBell(){
        System.out.println("Ringing the school bell...");
    }
}

class Classroom extends School {
		@Override
    public void ringBell() {
        System.out.println("Ringing the classroom bell...");
    }
}
```

이제 메인 함수에서 아래와 같은 코드를 실행시키면 어떻게 될까요?

```java
School school2 = new Classroom();
school2.ringBell()
```

```java
Ringing the classroom bell...
```

정적 바인딩과는 다르게 참조 형식은 School 인데 실행 메소드는 자식 클래스인 Classroom 의 메소드가 실행된 것을 확인할 수 있습니다.

왜 이렇게 동작하는 것일까요?

Java에서 객체는 객체 데이터 저장을 위한 메모리 외에 **테이블 주소를 저장하기 위한 메모리 4byte를 추가로 할당**받습니다. 이 가상 테이블은 클래스에 대한 정보(자신의 클래스 정보, 상속받은 부모 클래스 정보)를 저장한 테이블입니다.

오버라이딩을 통해 메소드를 재정의 했을 때, 가상테이블에서 **부모 클래스의 메소드 주소를 재정의한 메서드 주소로 매핑합니다**.

우리는 이 동적 바인딩을 통해 객체의 다형성을 형성할 수 있습니다.