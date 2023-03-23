## 상속 (Inheritance)

상속 (Inheritance)은 새로운 클래스를 정의할 때 이미 구현된 클래스의 속성과 기능을 물려받는 것을 말한다. extends 키워드를 사용하여 상속 관계를 정의할 수 있으며 `[상속받는 클래스] extends [상속하는 클래스]` 형식으로 표현된다.

<aside>
✏️ **상속하는 클래스**는 `상위`, `부모`, `수퍼`, `base` class 라고도 불리며,

**상속받는 클래스**는 `하위`, `자식`, `sub`, `derived` class 라고도 불린다.

</aside>

```java
// 부모 클래스
public class Parent { ... }

// 부모 클래스를 상속받은 자식 클래스
public class Child extends Parent { ... }
```

자식 클래스는 부모클래스의 필드와 메서드를 정의하지 않고 사용할 수 있다.

단, 예외가 존재한다.

1. 부모 클래스의 `private` 접근 제한을 가지는 필드 및 메소드는 자식이 물려받을 수 없다.
2. 부모와 자식클래스가 서로 다른 패키지에 있다면 부모의 `default` 접근 제한을 가지는 필드 및 메서드는 자식이 물려받을 수 없다.

```java
public class Member {

    public String name;

		Member() { }

    Member(String name) {
        this.name = name;
    }
}
```

```java
public class MemberVIP extends Member{

    MemberVIP(String name) {
        this.name = name
    }

}
```

```java
public static void main(String[] args) {
    Member member1 = new Member("member1");
    MemberVIP member2 = new MemberVIP("member2");

    System.out.println(member1.name);
    System.out.println(member2.name);
}
```

> member1
member2
>

`MemberVIP` 객체의 인스턴스인 `member2`는 `name`이라는 인스턴스 변수가 없을 것인데 어떻게 참조가 가능한 것일까?

사실 이 말은 틀렸다. `MemberVIP` 인스턴스는 `name` 이라는 인스턴스 변수를 가지고 있다. 이것을 이해하려면 생성자의 호출 순서를 알아야 한다.

```java
// 멤버 클래스의 기본 생성자에 출력문을 추가해준다.
Member() {
		System.out.println("Member 생성자 호출"); 
}
```

```java
// 멤버VIP 클래스의 생성자에도 출력문을 추가해준다.
MemberVIP(String name) {
		System.out.println("MemberVIP 생성자 호출");
		this.name = name;
}
```

```java
public static void main(String[] args) {
    MemberVIP member2 = new MemberVIP("member2");
}
```

> Member 생성자 호출
MemberVIP 생성자 호출
>

`MemberVIP` 인스턴스만 생성했을 뿐인데 `Member` 객체의 기본 생성자도 호출되었다.

메모리에 자식 클래스의 필드만 올라갔다면, 자식 객체로 부모 클래스에 존재하는 필드는 절대로 접근할 수 없다. 자식 클래스의 생성자 호출 시 아래의 순서로 메모리에 로딩된다.

1. 자식 클래스 객체는 자식 생성자를 호출
2. 자식 생성자를 호출하게 되면, 자동으로 부모 생성자가 먼저 호출
3. 부모 인스턴스가 먼저 메모리에 생성
4. 자식 인스턴스가 메모리에 있는 부모 필드를 참조하여 메모리에 생성

자식 클래스의 생성자가 명시적으로 선언되어 있지 않으면, 컴파일러는 자동으로 아래와 같은 기본 생성자를 생성한다.

```java
public 자식클래스() { super(); }
```

`super`는 자신이 상속받은 부모 클래스에 대한 레퍼런스 변수로, 부모 클래스의 멤버에 접근할 때 사용된다. `super()`는 자식 클래스의 생성자에서 부모 클래스의 생성자를 호출하기 위해 사용된다.

<aside>
✏️ 레퍼런스 변수는 메모리상에 생성된 인스턴스를 가리키는데 사용되는 변수이다.

</aside>