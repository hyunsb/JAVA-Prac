# Object

모든 클래스의 최상위(root) 클래스 모든 클래스는 object 클래스를 상속 받는다.

왜 object 클래스를 상속 받을까?

가장 큰 이유는 Object 클래스에 있는 메소드를 통해서 클래스의 기본적인 행동을 정의할 수 있기 때문이다. 클래스라면 이정도의 메소드는 정의되어 있어야 하고, 처리해 주어야 한다는 것. 그 기본이 Object 이기 때문에 Object를 상속받는다.

이건 무엇을 의미하는가? 모든 클래스는 Object 참조변수로 생성할 수 있다는 뜻!

[Java Development Kit Version 20 API Specification](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/lang/Object.html)

Object의 equals 메소드를 내맘대로 재정의 해보자

아래의 Object 클래스에 선언되어있는 equals 메소드이다. 두 객체가 서로 같은 주소를 가리키는지 판단하여 boolean 값을 반환한다.

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

두 객체가 가리키는 주소는 다르지만 임의의 기준 값이 같다면 논리적으로 같은 객체다 라고 반환해주는 메소드를 선언하고 싶다.

번호와 이름을 멤버로 가지는 Student 객체가 있다.

```java
class Student {
    private int number;
    private String name;

    Student(int number, String name) {
        this.number = number;
        this.name = name;
    }
}
```

세 개의 Student 객체는 모두 다른 인스턴스의 주소를 가리킨다.

```java
public static void main(String[] args) {
    Student student1 = new Student(1, "아이언맨");
    Student student2 = new Student(1, "아이언맨");
    Student student3 = new Student(2, "스파이더맨");

    System.out.println(student1.equals(student2)); // false
    System.out.println(student1.equals(student3)); // false
}
```

하지만 나는 number 가 같다면 논리적으로 같은 학생이다 라고 정의해주고 싶다. equals 함수를 재정의 해보자. 파라미터로 넘어온 객체가 Student 객체에 속하고 학생 번호가 같다면 true를 반환 하도록 재정의 했다.

```java
@Override
    public boolean equals(Object obj) {
        return (obj instanceof Student)
                && (Objects.equals(this.number, ((Student)obj).number));
    }
```

```java
public static void main(String[] args) {
    Student student1 = new Student(1, "아이언맨");
    Student student2 = new Student(1, "아이언맨");
    Student student3 = new Student(2, "스파이더맨");

    System.out.println(student1.equals(student2)); // true
    System.out.println(student1.equals(student3)); // false
}
```

이제 equal 메소드는 내가 원하는 대로 동작하게 되었다.