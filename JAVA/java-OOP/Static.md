# 자바 Static

자바에서 클래스와 변수, 메소드에 `static` 키워드를 사용할 수 있다.

여러 인스턴스가 공유하는 기준 값이 필요한 경우 사용한다.

`static` 키워드를 사용하면 해당 클래스의 여러 인스턴스에서 공유하는 데이터를 만들 수 있다.
또한, `static` 메소드를 사용하여 해당 클래스의 인스턴스를 생성하지 않고도 호출할 수 있다.

static 변수, 메소드는 클래스가 로딩될 때 메모리에 할당된다. 해당 클래스의 인스턴스가 생성되기 전에 이미 메모리에 존재한다.

따라서, static은 프로그램이 실행되는 동안 메모리에 한 번만 할당 되며, 이는 메모리 사용을 줄일 수 있고, 프로그램의 성능을 향상시킨다.

하지만, static 변수를 남발하는 것은 좋지 않다. static 변수가 많아지면, 프로그램의 복잡도가 증가할 수 있다. 또한 프로그램이 크기가 커질수록, static 변수가 차지하는 메모리 공간도 커진다.

## static 변수

static 변수는 클래스의 여러 인스턴스에서 공유하는 변수이다.

따라서, 한 인스턴스에서 해당 변수의 값을 변경하면 다른 인스턴스에서도 변경된 값을 가져올 수 있다. (조심하게 다뤄져야 한다.)

static 변수는 `Class`가 로드될 때 메모리(`Data`영역) 에 할당되며, 프로그램이 종료될 때까지 존재한다.

<aside>
✨ Data 영역: 데이터가 프로그램이 로드될 때 적재되며 언로드될 때 삭제되는 영역

</aside>

인스턴스 생성과 상관없이 사용가능하므로 클래스 이름으로 직접 참조가 가능하다.

아래의 코드로 static 변수가 여러 인스턴스에서 공통으로 사용되는지 확인할 수 있다.

```java
public class Employee {

    public static int serialNum = 1000;

    private int employeeId;
    private String employeeName;
    private String department;

		public Employee(){
				employeeId = serialNum++;
		}

    public int getEmployeeId() {
        return employee;
    }

    public void setEmployeeId(int employeeId) {
        this.employee = employee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Employee employeeLee = new Employee();
        employeeLee.setEmployeeName("이순신");

        Employee employeeKim = new Employee();
        employeeKim.setEmployeeName("김유신");
        employeeKim.serialNum++; // 인스턴스 변수로 참조하는 것은 좋은 방법이 아니다!

        System.out.println(Employee.serialNum); // 클래스명으로 참조하자
        System.out.println(employeeLee.serialNum);
        System.out.println(employeeKim.serialNum);

    }
}
```

`serialNum`을 참조할 시 모두 메모리의 `Data`영역에 존재하는 `serialNum`변수를 참조하여 같은 값을 출력하는 것을 확인할 수 있다.

```java
1001
1001
1001
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/22a4bb49-66eb-445e-811a-7be7bea7c94b/Untitled.png)

## static 메소드

static 메소드는 인스턴스를 생성하지 않고도 호출할 수 있는 메소드이다.

static 메소드는 **해당 클래스의 인스턴스 변수에 접근할 수 없으며**, 오직 **static 변수와 static 메소드만 접근 가능**하다.

따라서, static 메소드는 주로 유틸리티 메소드나 팩토리 메소드로 사용됩니다.

## static 사용 예시

```java
public class MyClass {
    public static int staticVar = 0;
    public int instanceVar = 0;

    public static void staticMethod() {
        System.out.println("This is a static method");

				System.out.println(staticVar);   // 정상작동!
				System.out.println(instanceVar); // 오류발생!
    }

    public void instanceMethod() {
        System.out.println("This is an instance method");
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass.staticVar = 1;
        MyClass.staticMethod();

        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();

        myClass1.instanceVar = 2;
        myClass2.instanceVar = 3;

        myClass1.instanceMethod();
        myClass2.instanceMethod();

        System.out.println(MyClass.staticVar);
    }
}

```

```java
This is a static method
This is an instance method 
This is an instance method
1
```