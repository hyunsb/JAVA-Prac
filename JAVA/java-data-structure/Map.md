### Map<K, V>

Key, Value 쌍으로 이루어진 구조, 해시 저장방식을 생각하면 편함

기본적으로 순서 유지 보장하지 않음

구현 클래스로는 HashMap, HashTable, TreeMap, LinkedHashMap이 있다.

### HashMap, HashTable

HashMap, HashTable(동기화, 스레드 안전, 속도 느림)

저장순서, 값에 의한 순서 보장하지 않음 .

전형적인 해시 저장방식

```java
transient Node<K,V>[] table;
```

`table` 멤버가 버킷이라고 생각, 내부 클래스인 `Node<K, V>`로 키, 값을 저장

```java
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;
				...
}
```

해시 충돌 시 `체이닝`방식으로 해결, 다음 노드를 가리키는 next 가변 멤버 존재.

### TreeMap

기본 값으로 값에 의한 오름차순 정렬, 그렇기에 추가, 삭제 연산이 HashMap보다 오래 걸림.

compareTo() 메서드 또는 외부에서 제공되는 Comparator에 따라 **키의 순서**에 따라 정렬.

레드-블랙 트리를 이용하여 값을 저장하고 키를 기준으로 정렬함.

레드-블랙 트리는 BST의 한 종류이기 때문에, 부모 노드보다 작은 값을 가지는 노드는 왼쪽, 큰 값을 가지는 노드는 오른쪽에 저장한다.

노드의 종류를 Red, Black으로 구분하고, 5가지 속성에 따라 구조를 변경시키며 트리의 편향을 방지한다.

### LinkedHashMap

삽입 순서를 유지함.

**double-linked buckets(이중 연결 버킷)** 으로 구현