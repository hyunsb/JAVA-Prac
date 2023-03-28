## 추상 클래스 (Abstract Class)

## 추상 클래스란?

- 구현 코드 없이 메서드의 선언만 있는 추상 메서드(abstract method)를 포함한 클래스
- 메서드 선언(declaration) : 반환타입, 메서드 이름, 매개변수로 구성
- 메서드 정의(definition) : 메서드 구현(implementation)과 동일한 의미 구현부(body) 를 가짐 ({ })
- 예) int add(int x, int y); // 선언  int add(int x, int y){ } // 구현부가 있음, 추상 메서드 아님
- abstract 예약어를 사용
- 추상 클래스는 new 할 수 없음 ( 인스턴스화 할 수 없음 )

## 추상 클래스 구현하기

- 메서드에 구현 코드가 없으면 abstract 로 선언
- abstract로 선언된 메서드를 가진 클래스는 abstract로 선언
- 모든 메서드가 구현 된 클래스라도 abstract로 선언되면 추상 클래스로 인스턴스화 할 수 없음
- 추상 클래스의 추상 메서드는 하위 클래스가 상속 하여 구현
- 추상 클래스 내의 추상 메서드 : 하위 클래스가 구현해야 하는 메서드
- 추상 클래스 내의 구현 된 메서드 : 하위 클래스가 공통으로 사용하는 메서드 ( 필요에 따라 하위 클래스에서 재정의 함 )
  Abstract 클래스는 인스턴스를 생성할 수 없으며, 반드시 상속을 통해 하위 클래스에서 구현되어야 합니다. Abstract 클래스는 일반적으로 추상 메소드를 포함하며, 하위 클래스에서 이러한 추상 메소드를 구현해야 합니다.

Abstract 클래스의 구현 예시를 살펴보면 다음과 같습니다.

```java
public abstract class Animal {
   public abstract void sound();
}

public class Dog extends Animal {
   public void sound() {
      System.out.println("멍멍");
   }
}

public class Cat extends Animal {
   public void sound() {
      System.out.println("야옹");
   }
}

```

위 예시에서 Animal 클래스는 추상 메소드인 sound()를 가지고 있으며, 하위 클래스인 Dog와 Cat에서 각각의 동물 소리를 출력하는 메소드를 구현하고 있습니다.

이처럼 Abstract 클래스는 하위 클래스에서 공통적인 메소드나 속성을 상속하고, 추상 메소드를 구현하여 다형성을 구현하는 데 유용하게 사용됩니다.

### 템플릿 메소드 패턴

Abstract 클래스를 이용한 템플릿 메소드 패턴은 소프트웨어 개발에서 자주 사용되는 디자인 패턴 중 하나입니다. 이 패턴은 Abstract 클래스를 사용하여 공통적인 알고리즘의 구조를 정의하고, 구체적인 구현은 하위 클래스에서 구현하도록 하는 것입니다.

예를 들어, 게임을 만들 때 캐릭터의 이동 알고리즘은 모든 캐릭터에 대해 동일합니다. 이때 Abstract 클래스를 이용하여 캐릭터의 이동 알고리즘의 구조를 정의하고, 실제 구현은 하위 클래스에서 구현하도록 할 수 있습니다.

```java
public abstract class Character {
   public void move() {
      // 공통적인 이동 알고리즘
      jump();
      run();
      turn();
   }

   public abstract void jump();
   public abstract void run();
   public abstract void turn();
}

public class Mario extends Character {
   public void jump() {
      System.out.println("마리오 점프!");
   }

   public void run() {
      System.out.println("마리오 뛰기!");
   }

   public void turn() {
      System.out.println("마리오 회전!");
   }
}

public class Luigi extends Character {
   public void jump() {
      System.out.println("루이지 점프!");
   }

   public void run() {
      System.out.println("루이지 뛰기!");
   }

   public void turn() {
      System.out.println("루이지 회전!");
   }
}

```

위 예시에서 Character 클래스는 이동 알고리즘의 구조를 정의하고 있으며, 실제 구현은 하위 클래스에서 구현하도록 하고 있습니다. 이를 통해 새로운 캐릭터를 추가할 때, 이동 알고리즘의 구조는 동일하지만 구체적인 구현은 하위 클래스에서 다르게 구현할 수 있습니다.

이처럼 Abstract 클래스를 이용하여 공통된 알고리즘의 구조를 정의하고 하위 클래스에서 구현하도록 하는 것은 코드의 재사용성과 유지보수성을 높이는 데에 유용하게 사용됩니다.

### 팩토리 메소드 패턴

Abstract 팩토리 메소드 패턴은 Abstract 클래스를 이용하여 객체 생성의 구조를 정의하고, 구체적인 구현은 하위 클래스에서 구현하도록 하는 패턴입니다. 이 패턴은 객체 생성의 구조를 변경하지 않고, 구체적인 객체 생성 방법을 다양화하고자 할 때 유용하게 사용됩니다.

예를 들어, 게임에서 아이템을 생성할 때, 아이템 생성의 구조는 동일하지만, 아이템의 종류에 따라 다양한 아이템을 생성해야 할 때가 있습니다. 이때 Abstract 팩토리 메소드 패턴을 사용하여 아이템 생성의 구조를 정의하고, 아이템의 종류별로 하위 클래스에서 구체적인 아이템 생성 방법을 구현하도록 할 수 있습니다.

```java
public abstract class ItemFactory {
   public Item createItem() {
      Item item = create();
      prepare(item);
      return item;
   }

   public abstract Item create();
   public abstract void prepare(Item item);
}

public class HealthPotionFactory extends ItemFactory {
   public Item create() {
      return new HealthPotion();
   }

   public void prepare(Item item) {
      // 체력 회복 능력치 설정
      item.setPower(50);
   }
}

public class ManaPotionFactory extends ItemFactory {
   public Item create() {
      return new ManaPotion();
   }

   public void prepare(Item item) {
      // 마나 회복 능력치 설정
      item.setPower(30);
   }
}

```

위 예시에서 ItemFactory 클래스는 아이템 생성의 구조를 정의하고 있으며, 실제 구현은 하위 클래스에서 다르게 구현할 수 있습니다. HealthPotionFactory 클래스와 ManaPotionFactory 클래스는 각각 체력 회복 능력치와 마나 회복 능력치를 다르게 설정하여 아이템을 생성하고 있습니다.

이처럼 Abstract 팩토리 메소드 패턴을 사용하여 객체 생성의 구조를 정의하고 하위 클래스에서 구체적인 구현을 다르게 구현할 수 있습니다. 이를 통해 객체 생성의 구조를 변경하지 않고, 다양한 객체를 생성할 수 있으므로 코드의 재사용성과 유지보수성을 높일 수 있습니다.

### 템플릿 메소드 패턴 vs 팩토리 메소드 패턴

템플릿 메소드 패턴과 팩토리 메소드 패턴의 차이점은 다음과 같습니다.

템플릿 메소드 패턴은 Abstract 클래스를 이용하여 공통된 알고리즘의 구조를 정의하고, 하위 클래스에서 구체적인 구현을 다르게 구현할 수 있도록 하는 패턴입니다. 이 패턴은 공통된 알고리즘의 구조를 변경하지 않고, 하위 클래스에서 구현을 다르게 하여 코드의 재사용성과 유지보수성을 높이는 데에 유용하게 사용됩니다.

반면에 팩토리 메소드 패턴은 Abstract 클래스를 이용하여 객체 생성의 구조를 정의하고, 구체적인 구현은 하위 클래스에서 구현하도록 하는 패턴입니다. 이 패턴은 객체 생성의 구조를 변경하지 않고, 구체적인 구현을 다르게 하여 객체를 생성하는 데에 유용하게 사용됩니다.

두 패턴은 모두 Abstract 클래스를 이용하여 공통된 구조를 정의하고, 하위 클래스에서 구체적인 구현을 다르게 하여 코드의 재사용성과 유지보수성을 높이는 데에 유용하게 사용됩니다. 그러나 템플릿 메소드 패턴은 공통된 알고리즘의 구조를 정의하는 데에 사용되며, 팩토리 메소드 패턴은 객체 생성의 구조를 정의하는 데에 사용됩니다.