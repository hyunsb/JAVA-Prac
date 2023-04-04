메소드를 호출할 때 파라미터를 전달하는 방법에는 두 가지가 있다.

- **`call by value`**: 메소드의 파라미터로 값을 넘겨주는 방식이다.
- **`call by reference`**: 메소드의 파라미터로 참조(주소)를 직접 넘겨주는 방식이다.

## **Call by Value**

**`Call by Value`**는 메소드가 파라미터로 받은 값을 복사하여 처리하는 방식이다.

따라서 호출된 메소드 안에서 인자 값이 변경되어도, 호출한 쪽에서는 영향이 없다.

당신은 복사가 되는 마법 사과를 가지고 있다.

당신은 나에게 그 사과를 복사해서 주었다.

나는 그 사과를 맛있게 먹었다.

당신의 사과는? 그대로 일 것이다.

다음과 같은 코드가 있다고 가정해보자.

```arduino

      <code />

public class CallByValueExample {
    public static void main(String[] args) {

        int num = 10;
        System.out.println("Before method call: " + num);// 10

        changeValue(num);
        System.out.println("After method call: " + num);// 10
    }

    public static void changeValue(int num) {
        num = 20;
    }
}
```

위 코드에서 **`changeValue`** 메소드는 인자로 받은 값을 20으로 변경하는 메소드이다.

하지만 main 메소드에서 호출한 뒤에도 num 변수의 값은 여전히 10이다.

이는 Call by Value 방식 때문이다.

당신은 **`chageValue`** 메소드 에게 사과를 복사해서 준 것이다.

그렇기 때문에 마법의 사과에는 아무런 영향이 없다.

즉, 자바에서는 메소드 호출 시 인자로 전달되는 값이 복사되어 전달되므로 메소드 안에서 인자 값이 변경되어도 호출한 쪽에서는 영향을 받지 않는다.

## **Call by Reference**

**`Call By Reference`**는 메소드에게 파라미터로 참조(주소)를 직접 전달하는 방식이다.

참조를 직접 전달하기 때문에 호출자의 변수와 수신자의 파라미터는 **`완전히 동일한`** 변수이다.

메소드 내에서 파라미터를 수정하면 원본 변수에도 영향을 끼친다.

당신은 사과나무를 가지고 있다.

나에게 그 사과나무의 위치를 알려주었다.

나는 그 사과나무의 사과 하나를 맛있게 먹었다.

당신의 사과나무는 사과 하나를 잃었다..

자바는 **`call by reference`**를 지원하지 않는다.

어? 뭔가 이상하다. 실험을 해보자

**`Person`**이라는 클래스는 **`age`**라는 변수를 멤버로 가진다.

```

      <code />

class Person{
    public int age;

    public Person(int age) {
        this.age = age;
    }
}
```

메인함수에서 **`Person`** 인스턴스를 생성하고 **`age`**에 20이라는 값을 초기화했다.

이후 **`changeAge`**메소드 참조변수를 넘겨주고, 해당 변수의 **`age`**를 30으로 변경했다.

```

      <code />

class Test{
        public static void main(String[] args) {
        Person person1 = new Person(20);
                System.out.println(person1.age);// 20

        changeAge(person1, 30);
        System.out.println(person1.age);// 30
    }

    static void changeAge(Person person, int age){
        Perrson.age = age;
    }
}
```

**`Call by Value`**라면 원본 변수의 값이 그대로여야 할 것이다.

하지만 30으로 변경된 것을 확인할 수 있다.

> 자바에서는 참조에 의한 호출(call by reference)을 직접적으로 지원하지는 않는다.하지만, 참조 타입 변수를 인자로 전달하는 경우에는 인자 값이 참조하는 객체가 가진 값에 대한 참조이기 때문에, 값이 변경될 수 있다.
>

말이 너무 어렵다…

인자 값이 참조하는 객체 ( = **`person1`**)

가 가진 값 ( = **`person1`**이 카리키는 **`Person`** 인스턴스)

에 대한 참조

즉, C/C++ 처럼 **`&`** 키워드로 주소 값을 직접적으로 넘겨준 건 아니다.

하지만 결과적으로 봤을 때 인자값이 참조하는 인스턴스와 ( = ChageAge 의 **`person`**)

원본 참조 변수가 가지는 인스턴스가 같게 되어 버린다. (= main함수의 **`person1`**)

이를 **`유사한 call by reference`** 방식이라고 부르기도 한다.

## ****자바는 Call by Reference 지원 안해****

```java
public class Person {
    private int age;

    public Person(int age){
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

```java
public class Test {
    public static void main(String[] args) {

        Person john = new Person(20);
        System.out.println(john.getAge()); // 20

        changeAge(john, 30);
        System.out.println(john.getAge()); // 30

    }

    public static void changeAge(Person person, int age){
        person.setAge(age);
    }
}
```

`changeAge` 메소드는 인자값으로 참조변수(john의 주소)를 넘겨 받았다.

그렇기 때문에 `chageAge` 메소드 내의 참조변수 `person`은 `john`이 가리키는 힙의 `Person` 인스턴스를 똑같이 가리키게 된다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f7d09240-e7a3-4d4a-960d-85f4a1787f42/Untitled.png)

그렇기에 `person.setAge()` 메소드가 수행되면 아래와 같이 원본 인스턴스가 변경되게 된다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f42f9634-994e-4632-bde6-c8eddef6b61b/Untitled.png)

아래의 코드는 어떨까

```java
public class Test {
    public static void main(String[] args) {

        Person john = new Person(20);
        System.out.println(john.getAge()); // 20

        changeAge(john, 30);
        System.out.println(john.getAge()); // 20

    }

    public static void changeAge(Person person, int age){
        person = new Person(age);
    }
}
```

`changeAge`의 `person`은 처음엔 위와 동일하게 `john`의 인스턴스를 가리킨다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4bd5e919-3424-47bf-a36c-d09739f03bbc/Untitled.png)

하지만 `person = new Person(age)`코드가 수행되면 아래와 같이 `person`은 더이상 `john`의 인스턴스가 아닌 새로 생성된 `Person`인스턴스를 가리키게 된다.

그러므로 원본 인스턴스는 변화에 영향을 받지 않는 것이다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/03af627a-39fa-4db9-8ce3-8ad5b53f435d/Untitled.png)