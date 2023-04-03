# String, StringBuilder, StringBuffer

## String

자바에서 문자열을 다루기 위한 클래스이다. 문자열을 저장하고, 검색하고, 변경하는 데 사용된다. String 클래스는 불변(immutable)이다. 따라서, String 객체를 변경하면, 새로운 객체가 생성되며, 원래의 객체는 변경되지 않는다.

String 객체를 생성하는 방법은 다음과 같다.

```java
String str1 = "Hello, world!";         // 문자열 리터럴을 이용하여 생성
String str2 = new String("Hello");     // new 연산자를 이용하여 생성

char[] charArray = {'J', 'a', 'v', 'a'};
String str3 = new String(charArray);   // char 배열을 이용하여 생성

```

아래는 String 클래스에서 제공하는 메서드 중 일부이다.

- `length()`: 문자열의 길이를 반환합니다.
- `charAt(int index)`: 지정한 인덱스에 있는 문자를 반환합니다.
- `substring(int beginIndex, int endIndex)`: 문자열의 일부를 잘라서 반환합니다.
- `equals(Object obj)`: 두 문자열이 같은지 비교합니다.
- `compareTo(String anotherString)`: 두 문자열을 사전순으로 비교합니다.
- 자바에서 두 개의 문자열을 연결하는 방법은 `+` 연산자 또는 `concat()` 메서드를 사용할 수 있습니다.

```java
String str1 = "Hello";
String str2 = "world";
String str3 = "Hello";

System.out.println(str1.length());           // 5
System.out.println(str1.charAt(0));          // H
System.out.println(str1.substring(1, 4));    // ell
System.out.println(str1.equals(str2));       // false
System.out.println(str1.equals(str3));       // true
System.out.println(str1.compareTo(str2));    // -15
System.out.println(str2.compareTo(str1));    // 15

String result = str1 + " " + str2; // "Hello world"
String result2 = str1.concat(" ").concat(str2); // "Hello world"

```

## StringBuilder, StringBuffer

String 클래스와는 달리 StringBuilder와 StringBuffer 클래스는 가변(mutable)이다. 따라서, 문자열을 변경할 때, 새로운 객체를 생성하지 않는다. StringBuilder와 StringBuffer 클래스는 거의 동일한 기능을 제공한다.

차이점은 `Thread-safe` 여부이다. StringBuffer는 `Thread-safe`하며, StringBuilder는 `Thread-safe`하지 않다. `thread-safe`는 멀티 스레드 환경에서 안전하게 실행될 수 있음을 의미한다.

StringBuilder와 StringBuffer 객체를 생성하는 방법은 다음과 같다.

```
StringBuilder sb1 = new StringBuilder("Hello");
StringBuffer sb2 = new StringBuffer("world");
```

아래는 StringBuilder와 StringBuffer 클래스에서 제공하는 메서드 중 일부이다.

- `append(String str)`: 문자열을 뒤에 추가합니다.
- `insert(int offset, String str)`: 문자열을 지정한 위치에 삽입합니다.
- `reverse()`: 문자열을 뒤집습니다.

```java
StringBuilder sb1 = new StringBuilder("Hello");
sb1.append(", world!");
System.out.println(sb1.toString());    // Hello, world!

StringBuffer sb2 = new StringBuffer("Hello");
sb2.insert(5, ", ");
System.out.println(sb2.toString());    // Hello, world!

StringBuilder sb3 = new StringBuilder("Hello");
sb3.reverse();
System.out.println(sb3.toString());    // olleH

```