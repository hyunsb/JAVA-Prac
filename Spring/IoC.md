## 제어의 역전(IoC)

사람은 이름을 가진다. 이름은 사람의 상태이다. 살아가면서 스스로 관리하며 사용한다. 하지만 이름은 태어나면서 부모님에 의해 결정된다. 나 스스로의 상태이며, 내가 사용하지만 이름의 제어권은 부모님에게 있다. 심지어 이름을 변경하는 것도 공공기관의 허가하에 이루어진다.

프로그래밍 상에서 살펴보자 자신을 소개하는 기능, 명함을 만드는 기능, 등등에서 자신이 사용하는 이름을 본인이 생성하여 제어하는 것이 아닌. 외부에서 생성자혹은 메서드를 통해 주입해준다.

```java
public class Person {
		private Name name;
		
		public Person(Name name) {
				this.name = name;
		}

		public setName(Name name) {
				this.name = name;
		}
}
```

이렇게 자신의 상태를 자신이 제어하지 못하는 것을 제어의 역전이라 한다.

또한 위와 같이 외부에 의해 의존성이 주입되는 것을 의존관계 주입(DI)라고 한다.

### 토비의 스프링 - IoC

초난감 DAO를 리팩토링하는 과정 후, UserDaoTest를 보자.

UserDao은 ConnectionMaker의 생성을 담당하는 기능이 외부로부터 의존성을 주입받는 방식으로 변경되었다. 그로 인해 UserDaoTest는 UserDao를 테스트하려고 만들었지만 ConnectionMaker의 생성이라는 책임까지 맡게 되었다.

이것도 이상하지 않은가? 분리시키도록 하자.

UserDao, ConnectionMaker의 생성을 팩토리 클래스로 분리하자. 이를 DaoFactory라 하겠다.

```java
public class DaoFactory {
		public UserDao userDao() {
				return new UserDao(new DConnectionMaker());
		}
}
```

이제 UserDaoTest는 해당 팩토리 클래스를 사용하여 테스트만 진행하면 된다.

```java
UserDao dao = new DaoFactory().userDao();
```

UserDao외의 AccountDao, MessageDao를 생성했다고 가정하자.

new DConnectionMaker() 부분이 중복될 것을 예상할 수 있다. 이부분은 메서드로 분리하자.

```java
public class DaoFactory {
		public UserDao userDao() {
				return new UserDao(connectionMaker());
		}

		public AccountDao accountDao() {
				return new AccountDao(connectionMaker());
		}

		public MessageDao messageDao() {
				return new MessageDao(connectionMaker());
		}

		private ConnectionMaker connectionMaker() {
				return new DConnectionMaker();
		}
}
```

UserDao는 자신이 자신의 멤버인 ConnectionMaker를 생성하는 제어권을 가지고 있었다. 하지만 지금은 DaoFactory에게 권한이 위임된 상태이다. 따라서 능동적인 객체에서 수동적인 객체로 변경되었다.

자신이 자신의 행동을 결정하는 것이 아닌 수동적으로 행동을 주입받는다.

자신의 행동의 제어권을 뺏기는 해당 상황을 `제어의 역전(IoC)`라고 한다.

### 스프링에서의 IoC

DaoFactory를 스프링에서 사용하게 하려면 어떻게 해야할까?

스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트를 빈(bean)이라 부른다. 스프링 빈은 스프링 컨테이너가 생성과 관계설정, 사용등을 제어 해주는데 이는 IoC가 적용된 사례라고 볼 수 있다.

스프링 빈의 제어를 담당하는 IoC 오브젝트를 빈 팩토리(bean factory)라 부른다. 보통은 이를 애플리케이션 전반에 걸쳐 모든 구성요소의 제어 역할을 담당하도록 확장한 애플리케이션 컨텍스트(application context)를 주로 사용한다.

자 DaoFactory를 빈 팩토리가 사용할 수 있는 설정 정보로 만들어보자.

```java
@Configuration // 애플리케이션 컨텍스트, 빈 팩토리가 사용할 설정 정보라는 표시
public class DaoFactory {

		@Bean // 오브젝트 생성을 담당하는 IoC용 메서드라는 표시
		public UserDao userDao() {
				return new UserDao(connectionMaker());
		}
	
		@Bean
		private ConnectionMaker connectionMaker() {
				return new DConnectionMaker();
		}
}
```

이제 UserDaoTest에서 이를 사용해보자

```java
public class UserDaoTest {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        
        UserDao dao = context.getBean("userDao", UserDao.class);
				...
    }
}
```

ApplicationContext(IoC 컨테이너, 스프링 컨테이너)를 생성하여, UserDao를 등록하고, 컨테이너를 통해 UserDao 클래스의 userDao메서드를 호출했다. 그런데 이러한 스프링의 기능 사용은 자바 코드와 별 다를 게 없어 보인다.

위의 코드에서는 그렇게 보일지도 모르지만 차이는 분명하게 존재한다. 먼저 빈을 통해 반환 받은 UserDao 인스턴스는 싱글톤이다.

Application Context는 `@Configuration`이 붙어있는 설정 파일의 `@Bean`이 붙은 메서드의 이름을 가져와 빈 목록을 만든다.

클라이언트가 `getBean()`메서드를 호출하면 빈 목록에서 요청한 이름의 메서드를 찾고 해당 빈을 생성하는 메서드를 호출하여 오브젝트를 생성하고 이를 반환한다.

이를 통해 얻을 수 있는 장점은 아래와 같다.

- 클라이언트는 구체적인 팩토리 클래스를 알 필요가 없다.

  일관된 방식으로 원하는 오브젝트 가져오기 가능

- 애플리케이션 컨텍스트는 종합 IoC 서비스를 제공한다.

  오브젝트 자동생성, 후처리, 정보의 조합, 설정 방식의 다변화, 인터셉팅 등

- 애플리케이션 컨텍스트는 빈을 검색하는 다양한 방법을 제공한다.

  타입으로 빈 검색, 특정 어노테이션이 설정되어 있는 빈 검색


### 용어 정리

- 스프링 빈

  스프링 컨테이너에서 관리되는 재사용 가능한 오브젝트

- 애플리케이션 컨텍스트

  빈 팩토리를 확장한 IoC 컨테이너, 빈을 등록하고 괸리하는 빈 팩토리의 기능과 부가적인 스프링 서비스를 제공함

- 컨테이너, IoC 컨테이너

  IoC 방식으로 빈을 관리한다는 의미에서 애플리케이션 컨텍스트나 빈 팩토리를 컨테이너라 부름.


---

## 싱글톤 레지스트리와 오브젝트 스코프

애플리케이션 컨텍스트(스프링 컨테이너)는 빈을 싱글톤으로 저장하고 관리하는 싱글톤 레지스트리이다.

스프링은 기본적으로 별다른 설정을 하지 않으면 내부에서 생성되는 빈 오브젝트를 모두 싱글톤으로 만든다.

자바의 싱글톤 패턴과 비슷한 개념이지만 구현 방법은 다르다. 자바의 싱글톤 패턴은 여러가지 문제점이 존재한다.

1. private 생성자로 인한 상속 불가능
2. private 생성자로 인해 외부에서 의존성을 생성자를 통해 주입하기 불가능.
3. 테스트하기 힘듦
4. 전역 상태로 사용되기 쉽기 때문에 객체지향에서 권장되지 않음.

위와 같은 여러가지 단점들이 존재하기 때문에 스프링은 직접 싱글톤 형태의 오브젝트를 생성하고 관리하는 기능을 제공한다. 그것이 싱글톤 레지스트리이다.

### 왜 빈 오브젝트를 싱글톤으로 만들까?

스프링이 주로 적용되는 대상이 서버환경이기 때문이다.

서버에서 매번 클라이언트의 요청에 따라 각 로직을 담당하는 오브젝트를 새로 만들어서 사용한다면? 서버의 부하가 심해질 것이다.

따라서 빈을 싱글톤으로 관리하며 대개 하나의 오브젝트만 만들어서 사용한다.

### 싱글톤 레지스트리

스프링 컨테이너는 static 메서드와 private 생성자를 사용해야하는 자바의 객체지향적인 클래스가 아닌 평범한 자바 클래스를 싱글톤으로 활용하게 해준다.

우리가 작성한 평범한 자바 클래스의 제어권을 컨테이너에게 넘기면 알아서 싱글톤 방식으로 만들어져 관리될 수 있게 해준다.

스프링 컨터이너는 빈을 인스턴스화 한 후, 해당 빈 인스턴스를 캐싱하여 재사용한다. 또한 빈의 초기화 및 소멸 작업은 적절한 콜백 메서드를 호출하여 처리된다.

<aside>
❓ 콜백 메서드
특정한 이벤트가 발생했을 때 개발자가 직접 호출하는 것이 아닌 시스템 또는 프레임워크에 의해 자동으로 호출되는 메서드

</aside>

### 스프링 빈의 스코프

스코프란 빈이 생성되고, 존재하고, 적용되는 범위라고 한다. 풀어서 설명하자면 해당 스코프를 설정하여 빈이 생성된 후 존재가 어디에서 유효한지에 대한 존재 범위를 조정할 수 있다.

대부분의 빈은 스프링 컨테이너에서 하나의 인스턴스가 생성되어 애플리케이션 전체에 공유된다는 스코프(범위)를 가진다.