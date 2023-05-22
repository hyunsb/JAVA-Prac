한 가지 영역에서 자기 할 일만 하는 개발자는 딱 그 수준에서 멈출 것.

### 환경 변수(Path)

어디에서든 실행할 수 있게 함

- 노트패드는 `Windows\Systme32` 경로에 존재함 하지만 `C:\` 에서도 notePad 명령어로 실행 가능

### choco

패키지(자바에서 클래스 모음) 관리자

[Installing Chocolatey](https://chocolatey.org/install#individual)

- 다운로드 + 인스톨 + 패스 등록 + 서비스 등록을 한번에 가능하게 함
- `.msi` 파일은 위의 동작을 한번에 실행함
1. 파워쉘 → `Get-ExecutionPolicy`
2. 결과가 **Restricted** 일 시,  **`Set-ExecutionPolicy AllSigned`** or **`Set-ExecutionPolicy Bypass -Scope Process`**

### MySQL

MySQL 8.xx → 오라클, SUN(썬) 인수합병 이후 버전

MySQL 5.xx → 인수합병 전이라 좋은 함수가 들어있지 않음.

Ctrl + Enter: 한줄 실행

Ctrl + Shift + Enter: 선택된 문단 실행

### 데이터베이스

- 자료를 통합하여 `중복`을 제거하고 응용소프트웨어에서 `공유`하여 사용할 수 있도록 체계화하여 저장한 것.
- 데이터베이스의 일관성이 깨지는 이유 → 중복되어서

  중복된 데이터 중 하나만 수정된 경우 일관성일 깨짐.

  TMI) 요즘 트렌드는 중복해서 저장함.

- DBMS 단점 → 서로 다른 집합들과 `관계`하지 못한다.

  이를 보완한 것이 RDBMS

- RDBMS → 데이터끼리 `참조`하여 사용할 수 있다.
    - ORACLE
    - MySQL
    - MariaDB
    - MSSQL

캐싱은 상대적인 것이다.

내가 엑세스해야하는 공간보다 더 가까이 두면 캐싱임

OS는 기본적으로 LRU 방식을 사용하기 때문에 자주 사용하는 것을 가까이 둠

I/O를 최대한 줄이자.

요즘은 알고리즘보단 가독성이 더욱 중요해졌음 왜? 개발자 인건비가 비싸지면서

### Sequencial Access

디스크에서 arm은 수직이동만 함. 수직으로 이동하는 것을 seek라고 함.

디스크는 1초에 1만번 회전함 (1/10000 초)

데이터를 찾을 때,

- 데이터가 유일하다는 보장이 없다면 full scan(풀 스캔) 해야함.
- 데이터가 유일하다는 보장이 있다면 데이터를 찾는 즉시 탐색 종료.

  하지만 풀스캔을 하지 않는다고 보장할 수는 없음.

  데이터가 마지막에 있을 수도 있기 때문.


### Random Access

index: 트랙: 3번, 값: 당근

바로 3번 트랙으로 seek하고 당근을 탐색함.