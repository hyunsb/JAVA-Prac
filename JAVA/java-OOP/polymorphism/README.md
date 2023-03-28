## 다형성

- 하나의 코드가 여러 자료형으로 구현되어 실행되는 것
- 같은 코드에서 여러 다른 실행 결과가 나옴
- 정보은닉, 상속과 더불어 객체지향 프로그래밍의 가장 큰 특징 중 하나임
- 다형성을 잘 활용하면 유연하고 확장성있고, 유지보수가 편리한 프로그램을 만들수 있음

```java
public abstract class Animal {
    abstract void move();
}
```

`Animal` 클래스를 상속 받아 `move()` 메소드를 재정의 하는 `Human`, `Tiger`, `Eagle` 클래스를 생성

```java
public class Human extends Animal{

    @Override
    public void move() {
        System.out.println("사람이 두발로 걷습니다.");
    }

}
```

```java
public class Tiger extends Animal{

    @Override
    void move() {
        System.out.println("호랑이가 네 발로 뜁니다.");
    }
}
```

```java
public class Eagle extends Animal{

    @Override
    void move() {
        System.out.println("독수리가 하늘을 날아갑니다.");
    }
}
```

`Animal` 클래스의 리스트를 가지고 관리하는 `AnimalController` 클래스 생성

```java
public class AnimalController {

    List<Animal> animals = new ArrayList<>();

    public void inputAnimal(Animal animal){
        animals.add(animal);
    }

    public void printAnimal(){
        for(Animal animal : animals)
            animal.move();
    }

}
```

다형성 테스트

```java
public class AnimalTest {

    public static void main(String[] args) {
        AnimalController animalController = new AnimalController();

        Animal animalHuman = new Human();
        Animal animalTiger = new Tiger();
        Animal animalEagle = new Eagle();

        animalController.inputAnimal(animalHuman);
        animalController.inputAnimal(animalTiger);
        animalController.inputAnimal(animalEagle);

        animalController.printAnimal();
    }
}
```

```java
사람이 두발로 걷습니다.
호랑이가 네 발로 뜁니다.
독수리가 하늘을 날아갑니다.
```

### 다운 캐스팅(downcasting)

- 업캐스팅된 클래스를 다시 원래의 타입으로 형 변환
- 하위 클래스로의 형 변환은 명시적으로 해야 함

```java
Customer vc = new VIPCustomer();              //묵시적
VIPCustomer vCustomer = (VIPCustomer)vc;      //명시적
```

### instanceof를 이용하여 인스턴스의 형 체크

- 원래 인스턴스의 형이 맞는지 여부를 체크하는 키워드 맞으면 true 아니면 false를 반환 함

```java
Human human = new Human();
if(human instanceof Animal) human.move();
```

```java
사람이 두발로 걷습니다.
```