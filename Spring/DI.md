## 의존관계 주입(DI; Dpendency Injection)

스프링 IoC 기능의 대표적인 동작원리는 의존관계 주입이라고 불린다.

A클래스가 B클래스에 의존한다. 라고 가정했을 때, B클래스에 변화가 A클래스에 영향을 미친다는 의미로 해적할 수 있다. 의존관계에는 방향성이 있다.

인터페이스와 의존관계를 맺으며 느슨한 의존관계를 유지할 수 있다. 인터페이스를 구현하는 모든 클래스와 런타임에 의존관계를 가질 수 있기 때문이다.

의존관계 주입의 핵심은 설계 시점(컴파일 시점)에는 알지 못했던 두 오브젝트(인스턴스)의 관계를 실행 시점(런타임)에 맺도록 도와주는 제3의 존재가 있다는 것이다. 외부에서 오브젝트 사이의 런타임 과계를 맺어주는 책임을 지닌 스프링의 스프링 컨테이너가 이 제3의 존재에 해당한다.

의존관계를 외부로 부터 주입받는 것이 아닌 스스로 검색을 이용한 의존관계 검색 방식도 존재한다.

```java
public class UserDao {
		private ConnectionMaker connectionMaker;

		public UserDao() {
				ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);

        this.connectionMaker = 
								context.getBean("connectionMaker", ConnectionMaker.class);
		}		
}
```

생성자로 의존성을 주입하더라도 스프링 컨테이너 내부적으로 적어도 한 번은 해당 방식으로 오브젝트를 가져와야 한다. 다행히 이런 복잡한 코드는 스프링이 제공하기 때문에 직접 구현하지 않아도 된다.

위와 같은 검색 방식은 검색하는 오브젝트는 빈일 필요가 없다는 특징이 있다. 위의 코드에서 UserDao는 빈으로 등록할 필요가 없고 ConnectionMaker만 빈이면 된다.

하지만 DI가 적용되기 위해서라면 UserDao도 컨테이너에서 빈으로 관리되어야 한다.