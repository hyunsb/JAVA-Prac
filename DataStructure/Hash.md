# 자료구조 - 해시

## Hash

해시는 key, value 쌍으로 이루어진 데이터 구조이다.

key는 구현된 hashFunction을 통해 계산된 해시 값(인덱스)으로 bukets에 저장된다.

```java
int myhashFunction(K key) {
		char[] chars = String.valueOf(key).toCharArray();
		int hash = 0;
		for(char c : chars) {
				hash += c * 31;
		}
		return hash * 31 % 1024;
}
```

hashFunction은 키를 인수로 받는 키를 구현된 로직으로 계산하여 일정 길이의 해시 값을 반환한다.

이러한 해시 값을 인덱스로 사용하여 값을 저장하고 검색한다.

데이터 접근 시 hashFunction으로 키를 계산하여 데이터에 접근하기 때문에 O(1)의 시간 복잡도를 가진다.

### 해시 함수의 값이 중복되면?

키 값이 다르게 주어졌는데 해시함수에서 동일한 해시 값이 반환되었을 때

즉, 다른 키 값에서 동일한 해시 값이 반환된다면 이를 해시 충돌이랑 한다.

해시 충돌이 일어날 경우 `chaining` 혹은 `open Addressing` 방식을 사용하여 데이터를 저장해야 한다.

위의 방식을 통해 데이터를 저장하게 된다면, 데이터에 접근하기 위해 소요되는 시간이 늘어나게 된다.

이는 해시의 성능을 가장 떨어뜨리는 주범이다.

### 좋은 해시 함수?

안타깝게도 해시 충돌은 절대로 피할 수 없다.

해시 충돌은 필연적으로 발생하는데, 버킷의 크기가 10개일 때 11개의 값을 넣어야 한다면 특정 인덱스에는 2개의 값이 들어갈 수 밖에 없다.

우리가 버킷을 아무리 크게 만든다고 한들 그것보다 많은 데이터가 들어온다면 해시 충돌을 피할 수 없는 것이다.

따라서 좋은 해시 함수란 해시충돌을 최소화하는 함수라고 할 수 있다.

비슷한 말로 키 값을 고르게 분포시키면 좋다. 키 값이 밀집되어 있다면 해시 충돌이 발생할 가능성이 높아지기 때문이다. 부가 적으로 빠른 계산을 할 수 있으면 더 좋은 해시 함수이다.

- 해시 함수 종류
    1. SHA-256 (Secure Hash Algorithm 256-bit)
        - 가장 널리 사용되는 해시 함수 중 하나입니다.
        - 안전한 데이터 저장, 디지털 서명, 암호화 등 다양한 보안 관련 작업에 사용됩니다.
        - 256-bit 해시 값으로 충돌이 발생할 확률이 매우 낮습니다.
    2. bcrypt
        - 주로 비밀번호 저장에 사용되는 해시 함수입니다.
        - 안전한 비밀번호 저장을 위해 솔트(salt)와 함께 사용되며, 해시 계산이 느리기 때문에 무차별 대입 공격을 어렵게 만듭니다.
        - 주로 웹 애플리케이션에서 사용됩니다.
    3. Argon2
        - 최신의 비밀번호 해시 함수 중 하나로, bcrypt와 유사한 목적으로 사용됩니다.
        - 메모리 집약적인 해시 함수로, 병렬 처리에 취약한 ASIC 공격을 어렵게 만들어 안전성을 높입니다.
        - 비밀번호 보안에 중점을 둔 애플리케이션에 적합합니다.
    4. MD5 (Message Digest Algorithm 5)
        - 매우 간단하고 빠른 해시 함수입니다.
        - 충돌 공격에 취약하므로 보안에는 적합하지 않지만, 일부 데이터 무결성 검사 등 간단한 용도로 사용될 수 있습니다.
    5. SHA-3 (Secure Hash Algorithm 3)
        - US NIST에 의해 표준으로 채택된 해시 함수입니다.
        - SHA-256과 같은 해시 알고리즘과는 다른 구조를 가지며, 안전한 데이터 저장과 디지털 서명에 사용됩니다.

### 피할 수 없다면 즐기자,, 해시 충돌 시 처리는?

앞서 말했듯 해시 충돌이 발생했을 때는 `Chaining` 혹은 `Open Addressing` 방식으로 처리해야 한다.

Chaining 방식은 해시 충돌 시 LinkedList 처럼 원래 있던 데이터가 다음 노드를 가리키고 해당 노드에 해시 충돌이 발생한 키의 값을 저장하는 방식이다.

해시 충돌로 인해 노드로 연결된 데이터들을 탐색하는 것을 스캔이라 하며 탐색 시간은 O(n) 이다.

최근에는 리스트가 아닌 트리를 사용하여 시간 복잡도를 줄였다.

Open Addressing 방식은 해시 충돌 시 다른 버킷에 데이터를 저장하는 방식이다.

- 선형 탐색
    - 해시 충돌 시 n칸을 건너 뛴 다음 버킷에 저장하는 방식
    - 계산이 단순하지만 탐색 시간이 많이 소요됨, 데이터들이 특정 위치에만 밀집(clustering)된다는 단점이 있다.
    - 데이터가 밀집 되기 때문에 해시 충돌이 연쇄적으로 발생할 수 있다.
- 제곱 탐색
    - n^2 칸을 건너 뛴 다음 버킷에 저장하는 방식
    - 데이터들이 특정 위치에 밀집하는 문제를 해결했다.
    - 하지만 이동 폭이 같기 때문에 데이터를 찾을 때 느리다는 단점이 있음.
- 이중 해시
    - 해시 값에 다른 해시 함수를 한번 더 적용
    - 최초의 해시 값을 구하는 함수, 충돌 발생 시 이동 폭을 구하는 함수

### 체이닝 방식을 이용한 해시 충돌 처리를 구현해보자

```java
public class MyLinkedHashTable<K, V> implements IHashTable<K, V>{

    private static final int DEFAULT_BUCKET_SIZE = 1024;

    private List<Node>[] buckets;
    private int size;
    private int bucketSize;
		
    public MyLinkedHashTable() {
        this.buckets = new List[DEFAULT_BUCKET_SIZE];
        this.bucketSize = DEFAULT_BUCKET_SIZE;
        this.size = 0;
				
				// 버킷을 링크드 리스트로 초기화 해준다.
        for (int i = 0; i < bucketSize; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }
		
		@Override
		public void put(K key, V value) {
        int idx = hash(key);
        List<Node> bucket = buckets[idx];
				
				// 만약 사용자가 입력한 키 값이 이미 등록되어 있다면 덮어쓴다. (hashMap 방식)
        for (Node node : bucket) {
            if (node.key.equals(key)){
                node.data = value;
            }
        }
				
				//링크드 리스트에 노드를 추가해준다.
        Node node = new Node(key, value);
        bucket.add(node);
        size++;
    }

		...
}
```