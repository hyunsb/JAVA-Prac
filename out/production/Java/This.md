## This

this 는 생성된 인스턴스의 메모리 주소를 가진다.

```java
public void setYear(int year) {
		this.year = year // this.year = 인스턴스의 변수, year = 매개 값
}
```

```java
public static void main(String args[]) {
		BirthDay day = new BirthDay();
		day.setYear(2000);
}
```

위와 같은 코드가 실행되면 메인 함수의 `args`와 `day`가 스택 메모리를 차지하게 되며 `BirthDay` 객체의 인스턴스인 `day`가 힙 메모리에 올라가게 된다.

다음으로 setYear() 메서드가 스택에 올라가게되고 `setYear` 메서드의 this.year은 힙 메모리에 적재된 BirthDay 인스턴스의 멤버변수를 가리킨다.

### 생성자에서 다른 생성자를 호출하는 경우

클래스에 생성자가 여러 개 인 경우, `this`를 이용하여 생성자에서 다른 생성자를 호출할 수 있다.

인스턴스의 생성은 호출된 메서드가 모두 종료된 시점에 이루어지므로 생성자에서 다른 생성자를 호출하는 경우 아래 코드와 같이 `this()` statement 이전에 다른 statement를 사용할 수 없다.

```java
public class Person {
    String name;
    int age;

    public Person(){
        this.name = "이름없음";

				// Call to 'this()' must be first statement in constructor body
        this("이름없음", 1); 
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### 자기 자신을 반환하는 경우

```java
public class Person {
    String name;
    int age;

    public Person(){
        this("이름없음", 1);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person getPerson(){
        return this;
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person();

        System.out.println(person1.getPerson());
        System.out.println(person2.getPerson());
    }
}
```

자신의 객체와 인스턴스 주소를 반환한다.

```java
Person@776ec8df
Person@4eec7777
```