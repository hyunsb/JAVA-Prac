## 형 변환 (Type Conversion)

자바에서는 데이터 타입 간에 형 변환이 가능하다. 크게 묵시적 형 변환과 명시적 형 변환이 있다.

**묵시적 형 변환 (자동 형 변환)**

- 작은 데이터 타입에서 큰 데이터 타입으로 대입하는 경우

    ```java
    int num = 10;
    double dNum = num; // int형 변수 num을 double형 변수 dNum에 대입
    
    ```

- 정수형 데이터 타입에서 실수형 데이터 타입으로 대입하는 경우

    ```java
    int num = 10;
    float fNum = num; // int형 변수 num을 float형 변수 fNum에 대입
    
    ```


**명시적 형 변환 (강제 형 변환, Type Casting)**

- 큰 데이터 타입에서 작은 데이터 타입으로 대입하는 경우

    ```java
    double dNum = 3.14;
    int num = (int) dNum; // double형 변수 dNum을 int형 변수 num에 대입
    
    ```

- 실수형 데이터 타입에서 정수형 데이터 타입으로 대입하는 경우

    ```java
    double dNum = 3.14;
    int num = (int) dNum; // double형 변수 dNum을 int형 변수 num에 대입
    
    ```

- 문자형 데이터 타입에서 정수형 데이터 타입으로 대입하는 경우

    ```java
    char ch = 'A';
    int num = (int) ch; // char형 변수 ch를 int형 변수 num에 대입
    
    ```